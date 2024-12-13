package com.cplerings.core.application.diamond.implementation;

import com.cplerings.core.application.diamond.DisableDiamondUseCase;
import com.cplerings.core.application.diamond.datasource.DisableDiamondDataSource;
import com.cplerings.core.application.diamond.error.DisableDiamondErrorCode;
import com.cplerings.core.application.diamond.input.DisableDiamondInput;
import com.cplerings.core.application.diamond.mapper.ADisableDiamondMapper;
import com.cplerings.core.application.diamond.output.DisableDiamondOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.shared.State;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class DisableDiamondUseCaseImpl extends AbstractUseCase<DisableDiamondInput, DisableDiamondOutput> implements DisableDiamondUseCase {

    private final ADisableDiamondMapper aDisableDiamondMapper;
    private final DisableDiamondDataSource disableDiamondDataSource;

    @Override
    protected DisableDiamondOutput internalExecute(UseCaseValidator validator, DisableDiamondInput input) {
        Diamond diamond = disableDiamondDataSource.getDiamondById(input.diamondId())
                .orElse(null);
        validator.validateAndStopExecution(diamond != null, DisableDiamondErrorCode.DIAMOND_NOT_FOUND);
        diamond.setState(State.INACTIVE);
        diamond = disableDiamondDataSource.save(diamond);
        return aDisableDiamondMapper.toOutput(diamond);
    }
}
