package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_EMAIL_FORMAT;
import static com.cplerings.core.application.account.error.AccountErrorCode.VERIFICATION_CODE_REQUIRED;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.account.input.VerifyCustomerInput;
import com.cplerings.core.application.design.CreateDesignVersionUseCase;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.input.CreateDesignVersionInput;
import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.input.InputValidator;
import com.cplerings.core.domain.design.DesignVersion;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateDesignVersionUseCaseImpl extends AbstractUseCase<CreateDesignVersionInput, CreateDesignVersionOutput>
        implements CreateDesignVersionUseCase {

    private CreateDesignVersionDataSource createDesignVersionDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateDesignVersionInput input) {
        super.validateInput(validator, input);
//        validator.validate(input.designFile() != null, DESIGN_FILE_REQUIRED);
//        validator.validate(input.previewImage() != null, IMAGE_REQUIRED);
//        validator.validate(input.versionNumber() > 0, INVALID_VERSION_NUMBER);
    }

    @Override
    protected CreateDesignVersionOutput internalExecute(UseCaseValidator validator, CreateDesignVersionInput input) {

//        DesignVersion designVersion = DesignVersion.builder()
//                .designFile(input.designFile())
//                .
//                .build();
        return null;
    }
}
