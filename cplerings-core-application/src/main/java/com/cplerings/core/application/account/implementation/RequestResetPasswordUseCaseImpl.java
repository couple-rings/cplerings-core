package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_NOT_IN_ACTIVE_STATE;
import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_EMAIL_FORMAT;

import com.cplerings.core.application.account.RequestResetPasswordUseCase;
import com.cplerings.core.application.account.datasource.RequestResetPasswordDataSource;
import com.cplerings.core.application.account.input.RequestResetPasswordInput;
import com.cplerings.core.application.account.output.RequestResetPasswordOutput;
import com.cplerings.core.application.shared.service.email.EmailInfo;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.application.shared.service.otp.OTPService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.input.InputValidator;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.ResetCodeStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class RequestResetPasswordUseCaseImpl extends AbstractUseCase<RequestResetPasswordInput, RequestResetPasswordOutput>
        implements RequestResetPasswordUseCase {

    private static final String SUBJECT_FORMAT_KEY = "requestResetPassword.text.subject";

    private final RequestResetPasswordDataSource requestResetPasswordDataSource;
    private final OTPService otpService;
    private final EmailService emailService;

    @Override
    protected void validateInput(UseCaseValidator validator, RequestResetPasswordInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
        validator.validate(InputValidator.emailIsValid(input.email()), INVALID_EMAIL_FORMAT);
    }

    @Override
    protected RequestResetPasswordOutput internalExecute(UseCaseValidator validator, RequestResetPasswordInput input) {
        final Optional<Account> accountOptional = requestResetPasswordDataSource.findByEmail(input.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        validator.validateAndStopExecution(account.getStatus() == AccountStatus.ACTIVE, ACCOUNT_NOT_IN_ACTIVE_STATE);
        final String otpCode = otpService.generate();
        final AccountPasswordReset accountPasswordReset = AccountPasswordReset.builder()
                .account(account)
                .status(ResetCodeStatus.PENDING)
                .code(otpCode)
                .build();
        requestResetPasswordDataSource.save(accountPasswordReset);
        emailService.sendMail(EmailInfo.builder()
                .recipient(input.email())
                .subject(String.format(LocaleUtils.translateLocale(SUBJECT_FORMAT_KEY), input.email()))
                .body(otpCode)
                .build());
        return RequestResetPasswordOutput.builder()
                .email(input.email())
                .build();
    }
}
