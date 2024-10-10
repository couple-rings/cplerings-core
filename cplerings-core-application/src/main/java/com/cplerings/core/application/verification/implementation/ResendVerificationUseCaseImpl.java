package com.cplerings.core.application.verification.implementation;

import static com.cplerings.core.application.shared.errorcode.ErrorCode.System.ERROR;
import static com.cplerings.core.application.verification.error.ResendVerificationErrorCode.ACCOUNT_NOT_IN_VERIFYING;
import static com.cplerings.core.application.verification.error.ResendVerificationErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.verification.error.ResendVerificationErrorCode.EMAIL_REQUIRED;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationCode;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.verification.ResendVerificationUseCase;
import com.cplerings.core.application.verification.datasource.ResendVerificationDataSource;
import com.cplerings.core.application.verification.input.ResendVerificationInput;
import com.cplerings.core.application.verification.mapper.AResendVerificationMapper;
import com.cplerings.core.application.verification.output.ResendVerificationOutput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ResendVerificationUseCaseImpl extends AbstractUseCase<ResendVerificationInput, ResendVerificationOutput>
        implements ResendVerificationUseCase {

    private final ResendVerificationDataSource resendVerificationDataSource;
    private final AccountVerificationService accountVerificationService;
    private final AResendVerificationMapper aResendVerificationMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ResendVerificationInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
    }

    @Override
    protected ResendVerificationOutput internalExecute(UseCaseValidator validator, ResendVerificationInput input) {
        final Account account = resendVerificationDataSource.getAccountByEmail(input.email())
                .orElse(null);
        validator.validateAndStopExecution(account != null, ACCOUNT_WITH_EMAIL_NOT_FOUND);
        validator.validateAndStopExecution(account.getStatus() == AccountStatus.VERIFYING, ACCOUNT_NOT_IN_VERIFYING);
        resendVerificationDataSource.disableAllPreviousCodes(account.getId());
        final VerificationCode verificationCode = accountVerificationService.generateVerificationCode(VerificationInfo.builder()
                .accountToVerify(account)
                .build());
        final VerificationCode.FailedReason failedReason = verificationCode.failedReason();
        if (failedReason != null) {
            if (failedReason == VerificationCode.FailedReason.INVALID_ARGUMENTS) {
                validator.validateAndStopExecution(false, ERROR);
            }
        }
        return aResendVerificationMapper.toOutput(account);
    }
}
