package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_EMAIL_FORMAT;
import static com.cplerings.core.application.account.error.AccountErrorCode.VERIFICATION_CODE_NOT_CREATED;
import static com.cplerings.core.application.account.error.AccountErrorCode.VERIFICATION_CODE_REQUIRED;

import com.cplerings.core.application.account.VerifyCustomerUseCase;
import com.cplerings.core.application.account.datasource.VerifyCustomerDataSource;
import com.cplerings.core.application.account.input.VerifyCustomerInput;
import com.cplerings.core.application.account.mapper.AVerifyCustomerMapper;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.mapper.JWTGenerationMapper;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.common.either.NoResult;
import com.cplerings.core.common.input.InputValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.VerificationCodeStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class VerifyCustomerUseCaseImpl extends AbstractUseCase<VerifyCustomerInput, AuthenticationTokenOutput> implements VerifyCustomerUseCase {

    private final VerifyCustomerDataSource verifyCustomerDataSource;
    private final AVerifyCustomerMapper verifyCustomerMapper;
    private final AccountVerificationService accountVerificationService;
    private final JWTGenerationService jwtGenerationService;
    private final JWTGenerationMapper jwtGenerationMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, VerifyCustomerInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
        validator.validate(InputValidator.emailIsValid(input.email()), INVALID_EMAIL_FORMAT);
        validator.validate(StringUtils.isNotBlank(input.verificationCode()), VERIFICATION_CODE_REQUIRED);
    }

    @Override
    protected AuthenticationTokenOutput internalExecute(UseCaseValidator validator, VerifyCustomerInput input) {
        final Optional<Account> accountOptional = verifyCustomerDataSource.getAccountByEmailWithMostRecentVerificationCode(input.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        Account account = accountOptional.get();
        final Optional<AccountVerification> accountVerificationOptional = account.getVerification()
                .stream()
                .findFirst();
        validator.validateAndStopExecution(accountVerificationOptional.isPresent(), VERIFICATION_CODE_NOT_CREATED);
        final AccountVerification accountVerification = accountVerificationOptional.get();
        final Either<NoResult, ErrorCodes> result = accountVerificationService.verifyAccount(VerificationInfo.builder()
                .accountToVerify(account)
                .verification(accountVerification)
                .verificationCode(input.verificationCode())
                .build());
        if (result.isRight()) {
            validator.submitErrorCodesAndThrow(result.getRight());
        }
        accountVerification.setStatus(VerificationCodeStatus.VERIFIED);
        verifyCustomerDataSource.saveAccountVerification(accountVerification);
        account.setStatus(AccountStatus.ACTIVE);
        account = verifyCustomerDataSource.saveAccount(account);
        final JWTGenerationInput jwtGenerationInput = jwtGenerationMapper.toInput(account);
        final String token = jwtGenerationService.generateToken(jwtGenerationInput);
        final String refreshToken = jwtGenerationService.generateRefreshToken(jwtGenerationInput);
        return verifyCustomerMapper.toOutput(token, refreshToken);
    }
}
