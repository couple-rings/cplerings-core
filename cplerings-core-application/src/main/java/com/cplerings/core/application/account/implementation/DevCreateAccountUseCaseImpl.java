package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.DevCreateAccountErrorCode.BRANCH_ID_REQUIRED;
import static com.cplerings.core.application.account.error.DevCreateAccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.DevCreateAccountErrorCode.INVALID_BRANCH_ID;
import static com.cplerings.core.application.account.error.DevCreateAccountErrorCode.PASSWORD_REQUIRED;
import static com.cplerings.core.application.account.error.DevCreateAccountErrorCode.ROLE_REQUIRED;
import static com.cplerings.core.application.account.error.DevCreateAccountErrorCode.USERNAME_REQUIRED;

import com.cplerings.core.application.account.DevCreateAccountUseCase;
import com.cplerings.core.application.account.datasource.DevCreateAccountDataSource;
import com.cplerings.core.application.account.input.DevCreateAccountInput;
import com.cplerings.core.application.account.output.DevCreateAccountOutput;
import com.cplerings.core.application.shared.mapper.AAccountMapper;
import com.cplerings.core.application.shared.mapper.AEnumMapper;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@UseCaseImplementation
@RequiredArgsConstructor
public class DevCreateAccountUseCaseImpl extends AbstractUseCase<DevCreateAccountInput, DevCreateAccountOutput> implements DevCreateAccountUseCase {

    private final DevCreateAccountDataSource dataSource;
    private final PasswordService passwordService;
    private final AEnumMapper enumMapper;
    private final AAccountMapper accountMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, DevCreateAccountInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.password()), PASSWORD_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.username()), USERNAME_REQUIRED);
        validator.validate(input.role() != null, ROLE_REQUIRED);
        validator.validate(input.branchId() != null, BRANCH_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.branchId()), INVALID_BRANCH_ID);
    }

    @Override
    protected DevCreateAccountOutput internalExecute(UseCaseValidator validator, DevCreateAccountInput input) {
        Account account = Account.builder()
                .email(input.email().trim())
                .password(passwordService.encryptPassword(input.password().trim()))
                .username(input.username().trim())
                .role(enumMapper.toRole(input.role()))
                .branch(dataSource.getBranchReferenceById(input.branchId()))
                .status(AccountStatus.ACTIVE)
                .build();
        account = dataSource.save(account);
        return DevCreateAccountOutput.builder()
                .account(accountMapper.toAccount(account))
                .build();
    }
}
