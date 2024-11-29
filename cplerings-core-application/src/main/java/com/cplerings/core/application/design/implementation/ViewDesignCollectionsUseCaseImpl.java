package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.design.ViewDesignCollectionsUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignCollectionsDataSource;
import com.cplerings.core.application.design.datasource.result.DesignCollections;
import com.cplerings.core.application.design.input.ViewDesignCollectionsInput;
import com.cplerings.core.application.design.mapper.AViewDesignCollectionsMapper;
import com.cplerings.core.application.design.output.ViewDesignCollectionsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDesignCollectionsUseCaseImpl extends AbstractUseCase<ViewDesignCollectionsInput, ViewDesignCollectionsOutput> implements ViewDesignCollectionsUseCase {

    private final ViewDesignCollectionsDataSource viewDesignCollectionsDataSource;
    private final AViewDesignCollectionsMapper aViewDesignCollectionsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDesignCollectionsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewDesignCollectionsOutput internalExecute(UseCaseValidator validator, ViewDesignCollectionsInput input) {
        DesignCollections designCollections = viewDesignCollectionsDataSource.getDesignCollections(input);
        return aViewDesignCollectionsMapper.toOutput(designCollections);
    }
}
