package com.cplerings.core.application.diamond.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.diamond.ViewDiamondsUseCase;
import com.cplerings.core.application.diamond.datasource.ViewDiamondsDataSource;
import com.cplerings.core.application.diamond.input.ViewDiamondsInput;
import com.cplerings.core.application.diamond.mapper.AViewDiamondsMapper;
import com.cplerings.core.application.diamond.output.ViewDiamondsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDiamondsUseCaseImpl extends AbstractUseCase<ViewDiamondsInput, ViewDiamondsOutput> implements ViewDiamondsUseCase {

    private final AViewDiamondsMapper aViewDiamondsMapper;
    private final ViewDiamondsDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDiamondsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewDiamondsOutput internalExecute(UseCaseValidator validator, ViewDiamondsInput input) {
        var result = dataSource.getDiamonds(input);
        return aViewDiamondsMapper.toOutput(result);
    }
}
