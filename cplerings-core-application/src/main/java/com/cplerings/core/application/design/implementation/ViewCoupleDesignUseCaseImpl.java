package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_COLLECTION_ID;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_METAL_SPECIFICATION_ID;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;
import static com.cplerings.core.application.design.error.DesignErrorCode.MIN_PRICE_LARGER_EQUAL_MAX_PRICE;

import com.cplerings.core.application.design.ViewCoupleDesignUseCase;
import com.cplerings.core.application.design.datasource.ViewCoupleDesignDataSource;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.mapper.AViewCoupleDesignMapper;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.common.pagination.PaginationUtils;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCoupleDesignUseCaseImpl extends AbstractUseCase<ViewCoupleDesignInput, ViewCoupleDesignOutput> implements ViewCoupleDesignUseCase {

    private final ViewCoupleDesignDataSource viewCoupleDesignDataSource;
    private final AViewCoupleDesignMapper aViewCoupleDesignMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCoupleDesignInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
        PaginationUtils.sanitizeFilterByPrice(input);
        if (NumberUtils.isZeroOrPositive(input.getMinPrice()) || NumberUtils.isZeroOrPositive(input.getMaxPrice())) {
            validator.validate(NumberUtils.isLessThan(input.getMinPrice(), input.getMaxPrice()), MIN_PRICE_LARGER_EQUAL_MAX_PRICE);
        }
        if (input.getCollectionId() != null) {
            validator.validate(input.getCollectionId() > 0, INVALID_COLLECTION_ID);
        }
        if (input.getMetalSpecificationId() != null) {
            validator.validate(input.getMetalSpecificationId() > 0, INVALID_METAL_SPECIFICATION_ID);
        }
    }

    @Override
    protected ViewCoupleDesignOutput internalExecute(UseCaseValidator validator, ViewCoupleDesignInput input) {
        var result = viewCoupleDesignDataSource.getDesignCouples(input);
        return aViewCoupleDesignMapper.toOutput(result);
    }
}
