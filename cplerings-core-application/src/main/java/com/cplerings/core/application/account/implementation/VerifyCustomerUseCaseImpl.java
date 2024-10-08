package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_NOT_IN_VERIFYING_STATE;
import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.EXPIRED_VERIFICATION_CODE;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_VERIFICATION_CODE;
import static com.cplerings.core.application.account.error.AccountErrorCode.VERIFICATION_CODE_NOT_CREATED;
import static com.cplerings.core.application.account.error.AccountErrorCode.VERIFICATION_CODE_REQUIRED;

import com.cplerings.core.application.account.VerifyCustomerUseCase;
import com.cplerings.core.application.account.datasource.VerifyCustomerDataSource;
import com.cplerings.core.application.account.input.CustomerVerificationInput;
import com.cplerings.core.application.account.mapper.AVerifyCustomerMapper;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.mapper.JWTGenerationMapper;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.service.verification.VerificationResult;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class VerifyCustomerUseCaseImpl extends AbstractUseCase<CustomerVerificationInput, AuthenticationTokenOutput> implements VerifyCustomerUseCase {

    private final VerifyCustomerDataSource verifyCustomerDataSource;
    private final AVerifyCustomerMapper verifyCustomerMapper;
    private final AccountVerificationService accountVerificationService;
    private final JWTGenerationService jwtGenerationService;
    private final JWTGenerationMapper jwtGenerationMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CustomerVerificationInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.verificationCode()), VERIFICATION_CODE_REQUIRED);
    }

    @Override
    protected AuthenticationTokenOutput internalExecute(UseCaseValidator validator, CustomerVerificationInput input) {
        final Optional<Account> accountOptional = verifyCustomerDataSource.getAccountByEmailWithMostRecentVerificationCode(input.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        validator.validateAndStopExecution((account.getStatus() == AccountStatus.VERIFYING), ACCOUNT_NOT_IN_VERIFYING_STATE);
        final Optional<AccountVerification> accountVerification = account.getVerification()
                .stream()
                .findFirst();
        validator.validateAndStopExecution(accountVerification.isPresent(), VERIFICATION_CODE_NOT_CREATED);
        final VerificationResult result = accountVerificationService.verifyAccount(VerificationInfo.builder()
                .accountToVerify(account)
                .verification(accountVerification.get())
                .build());
        if (result.status() == VerificationResult.Status.INVALID) {
            validator.validateAndStopExecution(false, INVALID_VERIFICATION_CODE);
        } else if (result.status() == VerificationResult.Status.EXPIRED) {
            validator.validateAndStopExecution(false, EXPIRED_VERIFICATION_CODE);
        }
        final JWTGenerationInput jwtGenerationInput = jwtGenerationMapper.toInput(account);
        final String token = jwtGenerationService.generateToken(jwtGenerationInput);
        final String refreshToken = jwtGenerationService.generateRefreshToken(jwtGenerationInput);
        return verifyCustomerMapper.toOutput(token, refreshToken);
    }
}
