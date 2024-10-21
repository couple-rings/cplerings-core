package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_EMAIL_FORMAT;
import static com.cplerings.core.application.account.error.AccountErrorCode.PASSWORD_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.USERNAME_REQUIRED;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.account.input.RegisterCustomerInput;
import com.cplerings.core.application.design.ViewCoupleDesignUseCase;
import com.cplerings.core.application.design.datasource.ViewCoupleDesignDataSource;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.mapper.AViewCoupleDesignMapper;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.input.InputValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCoupleDesignUseCaseImpl extends AbstractUseCase<ViewCoupleDesignInput, ViewCoupleDesignOutput> implements ViewCoupleDesignUseCase {

    private final ViewCoupleDesignDataSource viewCoupleDesignDataSource;
    private final AViewCoupleDesignMapper aViewCoupleDesignMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCoupleDesignInput input) {
        super.validateInput(validator, input);
//        validator.validate(input.getCollectionId() > 0, );
//        validator.validate(input.getMetalSpecificationId() > 0, );
    }

    @Override
    protected ViewCoupleDesignOutput internalExecute(UseCaseValidator validator, ViewCoupleDesignInput input) {
        var result = viewCoupleDesignDataSource.getDesignCouples(input);
        return aViewCoupleDesignMapper.toOutput(result);
    }
}
