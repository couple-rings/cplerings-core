package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_NOT_IN_VERIFYING_STATE;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_ALREADY_REGISTERED;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.PASSWORD_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.USERNAME_ALREADY_REGISTERED;
import static com.cplerings.core.application.account.error.AccountErrorCode.USERNAME_REQUIRED;
import static com.cplerings.core.application.shared.errorcode.ErrorCode.System.ERROR;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.account.RegisterCustomerUseCase;
import com.cplerings.core.application.account.datasource.RegisterCustomerDataSource;
import com.cplerings.core.application.account.input.RegisterCustomerInput;
import com.cplerings.core.application.account.mapper.ARegisterCustomerMapper;
import com.cplerings.core.application.account.output.CustomerRegistrationOutput;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.service.verification.AccountVerificationService;
import com.cplerings.core.application.shared.service.verification.VerificationCode;
import com.cplerings.core.application.shared.service.verification.VerificationInfo;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.Role;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class RegisterCustomerUseCaseImpl extends AbstractUseCase<RegisterCustomerInput, CustomerRegistrationOutput>
        implements RegisterCustomerUseCase {

    private final RegisterCustomerDataSource registerCustomerDataSource;
    private final PasswordService passwordService;
    private final AccountVerificationService accountVerificationService;
    private final ARegisterCustomerMapper aRegisterCustomerMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, RegisterCustomerInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.password()), PASSWORD_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.username()), USERNAME_REQUIRED);
    }

    @Override
    protected CustomerRegistrationOutput internalExecute(UseCaseValidator validator, RegisterCustomerInput input) {
        final String email = StringUtils.trim(input.email());
        validator.validateAndStopExecution(registerCustomerDataSource.emailIsNew(email), EMAIL_ALREADY_REGISTERED);
        final String username = StringUtils.trim(input.username());
        validator.validateAndStopExecution(registerCustomerDataSource.usernameIsNew(username), USERNAME_ALREADY_REGISTERED);
        final String password = StringUtils.trim(input.password());
        final String encryptedPassword = passwordService.encryptPassword(password);
        Account registeredAccount = aRegisterCustomerMapper.toAccount(input, encryptedPassword);
        registeredAccount.setRole(Role.CUSTOMER);
        registeredAccount.setStatus(AccountStatus.VERIFYING);
        registeredAccount = registerCustomerDataSource.save(registeredAccount);
        final VerificationCode verificationCode = accountVerificationService.generateVerificationCode(VerificationInfo.builder()
                .accountToVerify(registeredAccount)
                .build());
        final VerificationCode.FailedReason failedReason = verificationCode.failedReason();
        if (failedReason != null) {
            if (failedReason == VerificationCode.FailedReason.INVALID_ARGUMENTS) {
                validator.validateAndStopExecution(false, ERROR);
            } else if (failedReason == VerificationCode.FailedReason.ACCOUNT_NOT_IN_VERIFYING_STATUS) {
                validator.validateAndStopExecution(false, ACCOUNT_NOT_IN_VERIFYING_STATE);
            }
        }
        return aRegisterCustomerMapper.toOutput(registeredAccount);
    }
}
