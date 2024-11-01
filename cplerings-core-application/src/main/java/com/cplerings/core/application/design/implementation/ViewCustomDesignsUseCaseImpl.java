package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_COLLECTION_ID;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_METAL_SPECIFICATION_ID;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;
import static com.cplerings.core.application.design.error.DesignErrorCode.MIN_PRICE_LARGER_EQUAL_MAX_PRICE;

import java.util.Optional;

import com.cplerings.core.application.account.error.AccountErrorCode;
import com.cplerings.core.application.design.ViewCustomDesignsUseCase;
import com.cplerings.core.application.design.datasource.ViewCustomDesignsDataSource;
import com.cplerings.core.application.design.datasource.result.CustomDesigns;
import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.application.design.mapper.AViewCustomDesignsMapper;
import com.cplerings.core.application.design.output.ViewCustomDesignsOutput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomDesignsUseCaseImpl extends AbstractUseCase<ViewCustomDesignsInput, ViewCustomDesignsOutput> implements ViewCustomDesignsUseCase {

    private final ViewCustomDesignsDataSource viewCustomDesignsDataSource;
    private final AViewCustomDesignsMapper aViewCustomDesignsMapper;
    private final SecurityService securityService;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomDesignsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewCustomDesignsOutput internalExecute(UseCaseValidator validator, ViewCustomDesignsInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();
        validator.validateAndStopExecution(currentUser.authenticated(), AccountErrorCode.UNAUTHENTICATED);
        final Optional<Account> accountOptional = viewCustomDesignsDataSource.findAccountByEmail(currentUser.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        CustomDesigns designVersions = viewCustomDesignsDataSource.getCustomDesigns(account.getId(), input);
        return aViewCustomDesignsMapper.toOutput(designVersions);
    }
}
