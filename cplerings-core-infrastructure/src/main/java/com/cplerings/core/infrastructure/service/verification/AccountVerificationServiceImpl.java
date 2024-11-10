package com.cplerings.core.infrastructure.service.verification;

import static com.cplerings.core.application.shared.service.verification.VerificationCode.FailedReason.ACCOUNT_NOT_IN_VERIFYING_STATUS;
import static com.cplerings.core.application.shared.service.verification.VerificationCode.FailedReason.INVALID_ARGUMENTS;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.service.email.EmailInfo;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationCode;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.service.verification.error.AccountVerificationServiceErrorCode;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.common.either.NoResult;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.common.template.FileLoader;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.VerificationCodeStatus;
import com.cplerings.core.domain.shared.State;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Objects;

@Service
@Transactional(rollbackOn = Throwable.class)
@RequiredArgsConstructor
public class AccountVerificationServiceImpl implements AccountVerificationService {

    private static final int MAX_CODE_VALUE = 1000000;
    private static final String SUBJECT_FORMAT = LocaleUtils.translateLocale("accountVerificationService.text.subject");

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final String TEMPLATE_PATH = "template/account/otp-verification.html";

    private final EmailService emailService;
    private final AccountVerificationDataSource accountVerificationDataSource;

    @Value("${cplerings.verification-duration}")
    private int verificationDuration;

    @Override
    public Either<NoResult, ErrorCodes> verifyAccount(VerificationInfo verificationInfo) {
        if ((verificationInfo == null)
                || (verificationInfo.accountToVerify() == null)
                || (verificationInfo.verification() == null)) {
            return Either.right(ErrorCodes.create(ErrorCode.System.INPUT_REQUIRED));
        }
        final Account accountToVerify = verificationInfo.accountToVerify();
        if (accountToVerify.getStatus() != AccountStatus.VERIFYING) {
            return Either.right(ErrorCodes.create(AccountVerificationServiceErrorCode.ACCOUNT_NOT_IN_VERIFYING_STATUS));
        }
        final AccountVerification accountVerification = verificationInfo.verification();
        if (accountVerification.getState() != State.ACTIVE) {
            return Either.right(ErrorCodes.create(AccountVerificationServiceErrorCode.ACCOUNT_VERIFICATION_CODE_IS_INACTIVE));
        }
        if (accountVerification.getStatus() != VerificationCodeStatus.PENDING) {
            return Either.right(ErrorCodes.create(AccountVerificationServiceErrorCode.ACCOUNT_VERIFICATION_CODE_IS_USED));
        }
        if (accountVerificationCodeIsExpired(accountVerification)) {
            return Either.right(ErrorCodes.create(AccountVerificationServiceErrorCode.ACCOUNT_VERIFICATION_CODE_IS_EXPIRED));
        }
        if (Objects.equals(accountVerification.getCode(), verificationInfo.verificationCode())) {
            return Either.left(NoResult.INSTANCE);
        }
        return Either.right(ErrorCodes.create(AccountVerificationServiceErrorCode.INVALID_VERIFICATION_CODE));
    }

    private boolean accountVerificationCodeIsExpired(AccountVerification accountVerification) {
        final Instant now = TemporalUtils.getCurrentInstantUTC();
        final Instant accountVerificationCreatedAt = accountVerification.getCreatedAt();
        return accountVerificationCreatedAt.isBefore(now.minusSeconds(verificationDuration));
    }

    @Override
    public VerificationCode generateVerificationCode(VerificationInfo verificationInfo) {
        if (verificationInfo == null || verificationInfo.accountToVerify() == null) {
            return VerificationCode.builder()
                    .failedReason(INVALID_ARGUMENTS)
                    .build();
        }
        final Account account = verificationInfo.accountToVerify();
        if (account.getStatus() != AccountStatus.VERIFYING) {
            return VerificationCode.builder()
                    .failedReason(ACCOUNT_NOT_IN_VERIFYING_STATUS)
                    .build();
        }
        final String email = verificationInfo.accountToVerify().getEmail();
        final String verificationCode = generateCode();
        final AccountVerification accountVerification = AccountVerification.builder()
                .account(account)
                .code(verificationCode)
                .status(VerificationCodeStatus.PENDING)
                .build();
        accountVerificationDataSource.save(accountVerification);
        final String body = FileLoader.loadTemplate(TEMPLATE_PATH)
                .replace("{username}", account.getUsername())
                .replace("{otp}", verificationCode);
        emailService.sendMail(EmailInfo.builder()
                .recipient(email)
                .subject(String.format(SUBJECT_FORMAT, email))
                .body(body)
                .build());
        return VerificationCode.builder()
                .code(verificationCode)
                .build();
    }

    private static String generateCode() {
        int codeAsInt = SECURE_RANDOM.nextInt(0, MAX_CODE_VALUE);
        return String.format("%06d", codeAsInt);
    }
}
