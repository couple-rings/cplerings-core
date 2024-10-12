package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_NOT_IN_ACTIVE_STATE;
import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_RESET_PASSWORD_OTP;
import static com.cplerings.core.application.account.error.AccountErrorCode.NEW_PASSWORD_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.OTP_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.RESET_PASSWORD_OTP_ALREADY_USED;
import static com.cplerings.core.application.account.error.AccountErrorCode.RESET_PASSWORD_OTP_EXPIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.RESET_PASSWORD_OTP_NOT_CREATED;

import com.cplerings.core.application.account.ResetPasswordUseCase;
import com.cplerings.core.application.account.datasource.ResetPasswordDataSource;
import com.cplerings.core.application.account.input.ResetPasswordInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.ResetCodeStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class ResetPasswordUseCaseImpl extends AbstractUseCase<ResetPasswordInput, NoOutput>
        implements ResetPasswordUseCase {

    private final ResetPasswordDataSource resetPasswordDataSource;
    private final PasswordService passwordService;

    @Value("${cplerings.reset_password_duration}")
    private int resetPasswordDuration;

    @Override
    protected void validateInput(UseCaseValidator validator, ResetPasswordInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.newPassword()), NEW_PASSWORD_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.otp()), OTP_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, ResetPasswordInput input) {
        final Optional<Account> accountOptional = resetPasswordDataSource.findAccountByEmailWithMostRecentResetPasswordOTP(input.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        validator.validateAndStopExecution(account.getStatus() == AccountStatus.ACTIVE, ACCOUNT_NOT_IN_ACTIVE_STATE);
        final Optional<AccountPasswordReset> accountPasswordResetOptional = account.getPasswordResets()
                .stream()
                .findFirst();
        validator.validateAndStopExecution(accountPasswordResetOptional.isPresent(), RESET_PASSWORD_OTP_NOT_CREATED);
        final AccountPasswordReset accountPasswordReset = accountPasswordResetOptional.get();
        validator.validateAndStopExecution(accountPasswordReset.getStatus() != ResetCodeStatus.VERIFIED, RESET_PASSWORD_OTP_ALREADY_USED);
        validator.validateAndStopExecution(accountPasswordReset.getStatus() != ResetCodeStatus.EXPIRED, RESET_PASSWORD_OTP_EXPIRED);
        validator.validateAndStopExecution(accountPasswordResetIsNotExpired(accountPasswordReset), RESET_PASSWORD_OTP_EXPIRED);
        validator.validateAndStopExecution(StringUtils.equals(accountPasswordReset.getCode(), input.otp()), INVALID_RESET_PASSWORD_OTP);
        accountPasswordReset.setStatus(ResetCodeStatus.VERIFIED);
        resetPasswordDataSource.save(accountPasswordReset);
        account.setPassword(passwordService.encryptPassword(input.newPassword()));
        resetPasswordDataSource.save(account);
        return NoOutput.INSTANCE;
    }

    private boolean accountPasswordResetIsNotExpired(AccountPasswordReset accountPasswordReset) {
        final Instant now = TemporalUtils.getCurrentInstantUTC();
        final Instant accountPasswordResetCreatedAt = accountPasswordReset.getCreatedAt();
        return TemporalUtils.isAfterOrEqual(accountPasswordResetCreatedAt, now.minusSeconds(resetPasswordDuration));
    }
}
