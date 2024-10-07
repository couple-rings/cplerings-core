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

    private PlatformTransactionManager platformTransactionManager;

    @Mock
    private SessionMapper sessionMapper;

    @Mock
    private SessionImpl session;

    private TransactionManager transactionManager;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        transactionManager = Mockito.mock(TransactionManager.class);
        loginDataSource = Mockito.mock(LoginDataSource.class);
        passwordService = Mockito.mock(PasswordService.class);
        jwtGenerationService = Mockito.mock(JWTGenerationService.class);
        aRoleMapper = Mockito.mock(ARoleMapper.class);

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
        Mockito.when(loginDataSource.getLoginAccount(AccountTestConstant.CUSTOMER_EMAIL))
                .thenReturn(Optional.of(account));
        // Act
        Either<AuthenticationTokenOutput, ErrorCodes> result = loginUseCase.execute(input);
        List<ErrorCode> errorCodeList = result.getRight().getErrors().stream().toList();

        // Assert
        thenReturnAccountIsDisabledErrorcode(result, errorCodeList);
    }

    private void thenReturnAccountIsDisabledErrorcode(Either<AuthenticationTokenOutput, ErrorCodes> result, List<ErrorCode> errorCodeList) {
        Assertions.assertThat(result.isRight());
        Assertions.assertThat(errorCodeList.get(0)).isEqualTo(AuthenticationErrorCode.ACCOUNT_NOT_DISABLED);
    }

    @Test
    void givenAnyone_whenLoginWithUnVerifiedAccount() {
        LoginCredentialInput input = new LoginCredentialInput(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD);
        // Arrange
        Account account = Account.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .status(AccountStatus.VERIFYING)
                .build();
        Mockito.when(loginDataSource.getLoginAccount(AccountTestConstant.CUSTOMER_EMAIL))
                .thenReturn(Optional.of(account));
        // Act
        Either<AuthenticationTokenOutput, ErrorCodes> result = loginUseCase.execute(input);
        List<ErrorCode> errorCodeList = result.getRight().getErrors().stream().toList();

        // Assert
        thenReturnAccountIsUnVerifiedWithErrorcode(result, errorCodeList);
    }

    private void thenReturnAccountIsUnVerifiedWithErrorcode(Either<AuthenticationTokenOutput, ErrorCodes> result, List<ErrorCode> errorCodeList) {
        Assertions.assertThat(result.isRight());
        Assertions.assertThat(errorCodeList.get(0)).isEqualTo(AuthenticationErrorCode.ACCOUNT_NOT_VERIFIED);
    }
}
