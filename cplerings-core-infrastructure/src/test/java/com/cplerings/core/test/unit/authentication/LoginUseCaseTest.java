package com.cplerings.core.test.unit.authentication;

import static org.mockito.Mockito.when;

import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.application.authentication.implementation.LoginUseCaseImpl;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.mapper.ARoleMapper;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.transaction.TransactionManager;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.infrastructure.transaction.SessionImpl;
import com.cplerings.core.test.shared.AbstractTest;
import com.cplerings.core.test.shared.account.AccountTestConstant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class LoginUseCaseTest extends AbstractTest {

    @Mock
    private PasswordService passwordService;

    @Mock
    private JWTGenerationService jwtGenerationService;

    @Mock
    private ARoleMapper aRoleMapper;

    @Mock
    private LoginDataSource loginDataSource;

    @Mock
    private SessionImpl session;

    @Mock
    private TransactionManager transactionManager;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @BeforeEach
    void setUp() {
        loginUseCase.setTransactionManager(transactionManager);
        when(transactionManager.createSession(Mockito.any(SessionInformation.class))).thenReturn(session);
    }

    @Test
    void givenAnyone_whenLoginWithDisabledAccount() {
        // Arrange
        LoginCredentialInput input = new LoginCredentialInput(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD);
        Account account = Account.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .status(AccountStatus.INACTIVE)
                .build();
        List<ErrorCode> errorCodeList = null;

        // Mock
        when(loginDataSource.getLoginAccount(AccountTestConstant.CUSTOMER_EMAIL))
                .thenReturn(Optional.of(account));

        // Act
        Either<AuthenticationTokenOutput, ErrorCodes> result = loginUseCase.execute(input);
        if (result.isRight()) {
            errorCodeList = result.getRight().getErrors().stream().toList();
        }

        // Assert
        thenReturnAccountIsDisabledErrorCode(errorCodeList);
    }

    private void thenReturnAccountIsDisabledErrorCode(List<ErrorCode> errorCodeList) {
        Assertions.assertThat(errorCodeList).contains(AuthenticationErrorCode.ACCOUNT_DISABLED);
    }

    @Test
    void givenAnyone_whenLoginWithUnverifiedAccount() {
        // Arrange
        LoginCredentialInput input = new LoginCredentialInput(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD);
        Account account = Account.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .status(AccountStatus.VERIFYING)
                .build();
        List<ErrorCode> errorCodeList = null;

        // Mock
        when(loginDataSource.getLoginAccount(AccountTestConstant.CUSTOMER_EMAIL))
                .thenReturn(Optional.of(account));

        // Act
        Either<AuthenticationTokenOutput, ErrorCodes> result = loginUseCase.execute(input);
        if (result.isRight()) {
            errorCodeList = result.getRight().getErrors().stream().toList();
        }

        // Assert
        thenReturnAccountIsUnverifiedWithErrorCode(errorCodeList);
    }

    private void thenReturnAccountIsUnverifiedWithErrorCode(List<ErrorCode> errorCodeList) {
        Assertions.assertThat(errorCodeList).contains(AuthenticationErrorCode.ACCOUNT_NOT_VERIFIED);
    }
}
