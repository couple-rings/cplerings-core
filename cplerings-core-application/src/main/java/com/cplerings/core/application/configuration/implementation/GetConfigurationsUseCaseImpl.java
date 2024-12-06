package com.cplerings.core.application.configuration.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.configuration.GetConfigurationsUseCase;
import com.cplerings.core.application.configuration.datasource.GetConfigurationsDataSource;
import com.cplerings.core.application.configuration.input.GetConfigurationsInput;
import com.cplerings.core.application.configuration.mapper.AGetConfigurationsMapper;
import com.cplerings.core.application.configuration.output.GetConfigurationsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetConfigurationsUseCaseImpl extends AbstractUseCase<GetConfigurationsInput, GetConfigurationsOutput> implements GetConfigurationsUseCase {

    private final AGetConfigurationsMapper aGetConfigurationsMapper;
    private final GetConfigurationsDataSource getConfigurationsDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, GetConfigurationsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected GetConfigurationsOutput internalExecute(UseCaseValidator validator, GetConfigurationsInput input) {
        var result = getConfigurationsDataSource.getConfigurations(input);
        return aGetConfigurationsMapper.toOutput(result);
    }
}
