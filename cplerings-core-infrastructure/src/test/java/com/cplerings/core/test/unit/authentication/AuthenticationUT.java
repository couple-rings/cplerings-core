package com.cplerings.core.test.unit.authentication;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.PlatformTransactionManager;

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
import com.cplerings.core.infrastructure.transaction.SessionMapper;
import com.cplerings.core.test.shared.account.AccountTestConstant;

class AuthenticationUT {

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

    private LoginUseCaseImpl loginUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LoginUseCaseImpl loginUseCaseImpl = new LoginUseCaseImpl(
                loginDataSource,
                passwordService,
                jwtGenerationService,
                aRoleMapper
        );
        loginUseCaseImpl.setTransactionManager(transactionManager);
        loginUseCase = loginUseCaseImpl;
        Mockito.when(transactionManager.createSession(Mockito.any(SessionInformation.class)))
                .thenReturn(session);
    }

    @Test
    void givenAnyone_whenLoginWithDisabledAccount() {

        LoginCredentialInput input = new LoginCredentialInput(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD);
        // Arrange
        Account account = Account.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .status(AccountStatus.INACTIVE)
                .build();
        List<ErrorCode> errorCodeList = null;

        // Mock
        Mockito.when(loginDataSource.getLoginAccount(AccountTestConstant.CUSTOMER_EMAIL))
                .thenReturn(Optional.of(account));
        // Act
        Either<AuthenticationTokenOutput, ErrorCodes> result = loginUseCase.execute(input);
        if (result.isRight()) {
            errorCodeList = result.getRight().getErrors().stream().toList();
        }

        // Assert
        thenReturnAccountIsDisabledErrorcode(errorCodeList);
    }

    private void thenReturnAccountIsDisabledErrorcode(List<ErrorCode> errorCodeList) {
        Assertions.assertThat(errorCodeList).contains(AuthenticationErrorCode.ACCOUNT_DISABLED);
    }

    @Test
    void givenAnyone_whenLoginWithUnverifiedAccount() {
        LoginCredentialInput input = new LoginCredentialInput(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD);
        // Arrange
        Account account = Account.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .status(AccountStatus.VERIFYING)
                .build();
        List<ErrorCode> errorCodeList = null;

        // Mock
        Mockito.when(loginDataSource.getLoginAccount(AccountTestConstant.CUSTOMER_EMAIL))
                .thenReturn(Optional.of(account));
        // Act
        Either<AuthenticationTokenOutput, ErrorCodes> result = loginUseCase.execute(input);
        if (result.isRight()) {
            errorCodeList = result.getRight().getErrors().stream().toList();
        }

        // Assert
        thenReturnAccountIsUnverifiedWithErrorcode(errorCodeList);
    }

    private void thenReturnAccountIsUnverifiedWithErrorcode(List<ErrorCode> errorCodeList) {
        Assertions.assertThat(errorCodeList).contains(AuthenticationErrorCode.ACCOUNT_NOT_VERIFIED);
    }
}
