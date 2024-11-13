package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.CreateCustomDesignErrorCode.INVALID_DOCUMENT;

import com.cplerings.core.application.design.CreateDesignVersionUseCase;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.error.CreateDesignVersionErrorCode;
import com.cplerings.core.application.design.input.CreateDesignVersionInput;
import com.cplerings.core.application.design.mapper.ACreateDesignVersionMapper;
import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateDesignVersionUseCaseImpl extends AbstractUseCase<CreateDesignVersionInput, CreateDesignVersionOutput>
        implements CreateDesignVersionUseCase {

    private final CreateDesignVersionDataSource createDesignVersionDataSource;
    private final ACreateDesignVersionMapper aCreateDesignVersionMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateDesignVersionInput input) {
        super.validateInput(validator, input);
        validator.validate(input.designFileId() != null, CreateDesignVersionErrorCode.DESIGN_FILE_REQUIRED);
        validator.validate(input.previewImageId() != null, CreateDesignVersionErrorCode.IMAGE_REQUIRED);
        validator.validate(input.designId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
        validator.validate(input.customerId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected CreateDesignVersionOutput internalExecute(UseCaseValidator validator, CreateDesignVersionInput input) {
        Design design = createDesignVersionDataSource.getDesignByID(input.designId())
                .orElse(null);
        validator.validateAndStopExecution(design != null, CreateDesignVersionErrorCode.INVALID_DESIGN_ID);
        Account customer = createDesignVersionDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CreateDesignVersionErrorCode.INVALID_CUSTOMER_ID);

        Document document = createDesignVersionDataSource.getDocumentById(input.designFileId())
                .orElse(null);
        validator.validateAndStopExecution(document != null, CreateDesignVersionErrorCode.INVALID_DOCUMENT);
        Image image = createDesignVersionDataSource.getImageById(input.previewImageId())
                .orElse(null);
        validator.validateAndStopExecution(image != null, CreateDesignVersionErrorCode.INVALID_IMAGE);
        int versionNumber = 1;
        if (design.getDesignVersions() != null && design.getDesignVersions().size() > 0)
            versionNumber = design.getDesignVersions().size() + 1;
        DesignVersion designVersion = DesignVersion.builder()
                .designFile(document)
                .customer(customer)
                .image(image)
                .design(design)
                .versionNumber(versionNumber)
                .isAccepted(false)
                .isOld(false)
                .build();
        DesignVersion designVersionCreated = createDesignVersionDataSource.save(designVersion);
        return aCreateDesignVersionMapper.toOutput(designVersionCreated);
    }
}
