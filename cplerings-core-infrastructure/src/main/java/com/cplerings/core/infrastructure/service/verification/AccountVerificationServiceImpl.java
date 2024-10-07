package com.cplerings.core.infrastructure.service.verification;

import static com.cplerings.core.application.shared.service.verification.VerificationCode.FailedReason.ACCOUNT_NOT_IN_VERIFYING_STATUS;
import static com.cplerings.core.application.shared.service.verification.VerificationCode.FailedReason.INVALID_ARGUMENTS;

import com.cplerings.core.application.shared.service.email.EmailInfo;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationCode;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.service.verification.VerificationResult;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.VerificationCodeStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.security.SecureRandom;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class AccountVerificationServiceImpl implements AccountVerificationService {

    private static final int MAX_CODE_VALUE = 1000000;
    private static final String SUBJECT_FORMAT = LocaleUtils.translateLocale("accountVerificationService.text.subject");

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final EmailService emailService;
    private final AccountVerificationDataSource accountVerificationDataSource;

    @Override
    public VerificationResult verifyAccount(VerificationInfo verificationInfo) {
        return null;
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
        emailService.sendMail(EmailInfo.builder()
                .recipient(email)
                .subject(String.format(SUBJECT_FORMAT, email))
                .body(verificationCode)
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
