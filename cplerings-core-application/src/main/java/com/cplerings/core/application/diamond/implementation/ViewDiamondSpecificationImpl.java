package com.cplerings.core.application.diamond.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.diamond.ViewDiamondSpecificationUseCase;
import com.cplerings.core.application.diamond.datasource.ViewDiamondSpecificationDataSource;
import com.cplerings.core.application.diamond.datasource.result.DiamondSpecifications;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.diamond.mapper.AViewDiamondSpecificationMapper;
import com.cplerings.core.application.diamond.output.ViewDiamondSpecificationOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewDiamondSpecificationImpl extends AbstractUseCase<ViewDiamondSpecificationInput, ViewDiamondSpecificationOutput> implements ViewDiamondSpecificationUseCase {

    private final ViewDiamondSpecificationDataSource viewDiamondSpecificationDataSource;
    private final AViewDiamondSpecificationMapper aViewDiamondSpecificationMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDiamondSpecificationInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewDiamondSpecificationOutput internalExecute(UseCaseValidator validator, ViewDiamondSpecificationInput input) {
        DiamondSpecifications result = viewDiamondSpecificationDataSource.getDiamondSpecifications(input);
        return aViewDiamondSpecificationMapper.toOutput(result);
    }
}
