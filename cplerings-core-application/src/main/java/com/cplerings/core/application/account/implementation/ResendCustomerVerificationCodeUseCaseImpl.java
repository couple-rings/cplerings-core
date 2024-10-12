package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_NOT_IN_VERIFYING_STATE;
import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.shared.errorcode.ErrorCode.System.ERROR;

import com.cplerings.core.application.account.ResendCustomerVerificationCodeUseCase;
import com.cplerings.core.application.account.datasource.ResendCustomerVerificationCodeDataSource;
import com.cplerings.core.application.account.input.ResendCustomerVerificationCodeInput;
import com.cplerings.core.application.account.mapper.AResendCustomerVerificationCodeMapper;
import com.cplerings.core.application.account.output.ResendCustomerVerificationCodeOutput;
import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationCode;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@UseCaseImplementation
@RequiredArgsConstructor
public class ResendCustomerVerificationCodeUseCaseImpl extends AbstractUseCase<ResendCustomerVerificationCodeInput, ResendCustomerVerificationCodeOutput>
        implements ResendCustomerVerificationCodeUseCase {

    private final ResendCustomerVerificationCodeDataSource resendCustomerVerificationCodeDataSource;
    private final AccountVerificationService accountVerificationService;
    private final AResendCustomerVerificationCodeMapper aResendCustomerVerificationCodeMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ResendCustomerVerificationCodeInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
    }

    @Override
    protected ResendCustomerVerificationCodeOutput internalExecute(UseCaseValidator validator, ResendCustomerVerificationCodeInput input) {
        final Account account = resendCustomerVerificationCodeDataSource.getAccountByEmail(input.email())
                .orElse(null);
        validator.validateAndStopExecution(account != null, ACCOUNT_WITH_EMAIL_NOT_FOUND);
        validator.validateAndStopExecution(account.getStatus() == AccountStatus.VERIFYING, ACCOUNT_NOT_IN_VERIFYING_STATE);
        resendCustomerVerificationCodeDataSource.disableAllPreviousCodes(account.getId());
        final VerificationCode verificationCode = accountVerificationService.generateVerificationCode(VerificationInfo.builder()
                .accountToVerify(account)
                .build());
        final VerificationCode.FailedReason failedReason = verificationCode.failedReason();
        if (failedReason != null) {
            if (failedReason == VerificationCode.FailedReason.INVALID_ARGUMENTS) {
                validator.validateAndStopExecution(false, ERROR);
            }
        }
        return aResendCustomerVerificationCodeMapper.toOutput(account);
    }
}
