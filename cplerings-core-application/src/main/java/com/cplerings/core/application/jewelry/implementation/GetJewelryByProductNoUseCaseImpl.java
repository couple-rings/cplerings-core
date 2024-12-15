package com.cplerings.core.application.jewelry.implementation;

import com.cplerings.core.application.jewelry.GetJewelryByProductNoUseCase;
import com.cplerings.core.application.jewelry.datasource.GetJewelryByProductNoDataSource;
import com.cplerings.core.application.jewelry.error.GetJewelryByProductNoErrorCode;
import com.cplerings.core.application.jewelry.input.GetJewelryByProductNoInput;
import com.cplerings.core.application.jewelry.mapper.AGetJewelryByProductNoMapper;
import com.cplerings.core.application.jewelry.output.GetJewelryByProductNoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.jewelry.Jewelry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetJewelryByProductNoUseCaseImpl extends AbstractUseCase<GetJewelryByProductNoInput, GetJewelryByProductNoOutput> implements GetJewelryByProductNoUseCase {

    private final GetJewelryByProductNoDataSource dataSource;
    private final AGetJewelryByProductNoMapper aMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, GetJewelryByProductNoInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.productNo() != null, GetJewelryByProductNoErrorCode.JEWELRY_PRODUCT_NO_REQUIRED);
    }

    @Override
    protected GetJewelryByProductNoOutput internalExecute(UseCaseValidator validator, GetJewelryByProductNoInput input) {
        Jewelry jewelry = dataSource.getJewelryByProductNo(input.productNo())
                .orElse(null);
        validator.validateAndStopExecution(jewelry != null, GetJewelryByProductNoErrorCode.JEWELRY_NOT_FOUND);
        return aMapper.toOutput(jewelry);
    }
}
