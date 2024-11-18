package com.cplerings.core.application.agreement.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.agreement.ViewAgreementsUseCase;
import com.cplerings.core.application.agreement.datasource.ViewAgreementsDataSource;
import com.cplerings.core.application.agreement.input.ViewAgreementsInput;
import com.cplerings.core.application.agreement.mapper.AViewAgreementsMapper;
import com.cplerings.core.application.agreement.output.ViewAgreementsOutput;
import com.cplerings.core.application.diamond.datasource.result.DiamondSpecifications;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewAgreementsUseCaseImpl extends AbstractUseCase<ViewAgreementsInput, ViewAgreementsOutput> implements ViewAgreementsUseCase {

    private final ViewAgreementsDataSource viewAgreementsDataSource;
    private final AViewAgreementsMapper aViewAgreementsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewAgreementsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewAgreementsOutput internalExecute(UseCaseValidator validator, ViewAgreementsInput input) {
        var result = viewAgreementsDataSource.getAgreements(input);
        return aViewAgreementsMapper.toOutput(result);
    }
}
