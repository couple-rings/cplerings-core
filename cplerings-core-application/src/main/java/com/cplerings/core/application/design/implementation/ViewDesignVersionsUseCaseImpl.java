package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewDesignVersionsUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignVersionsDataSource;
import com.cplerings.core.application.design.datasource.result.DesignVersions;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.application.design.mapper.AViewDesignVersionsMapper;
import com.cplerings.core.application.design.output.ViewDesignVersionsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDesignVersionsUseCaseImpl extends AbstractUseCase<ViewDesignVersionsInput, ViewDesignVersionsOutput> implements ViewDesignVersionsUseCase {

    private final AViewDesignVersionsMapper aViewDesignVersionsMapper;
    private final ViewDesignVersionsDataSource viewDesignVersionsDataSource;

    @Override
    protected ViewDesignVersionsOutput internalExecute(UseCaseValidator validator, ViewDesignVersionsInput input) {
        DesignVersions designVersions = viewDesignVersionsDataSource.findDesignVersions(input);
        return aViewDesignVersionsMapper.toOutput(designVersions);
    }
}
