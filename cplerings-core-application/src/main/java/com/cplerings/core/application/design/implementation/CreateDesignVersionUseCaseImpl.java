package com.cplerings.core.application.design.implementation;

import java.util.List;

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
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
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
        validator.validate(input.femaleVersion().designFileId() != null, CreateDesignVersionErrorCode.DESIGN_FILE_REQUIRED);
        validator.validate(input.femaleVersion().previewImageId() != null, CreateDesignVersionErrorCode.IMAGE_REQUIRED);
        validator.validate(input.femaleVersion().designId() != null, CreateDesignVersionErrorCode.DESIGN_ID_REQUIRED);
        validator.validate(input.femaleVersion().customerId() != null, CreateDesignVersionErrorCode.CUSTOMER_ID_REQUIRED);
        validator.validate(input.femaleVersion().designId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
        validator.validate(input.femaleVersion().customerId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
        validator.validate(input.maleVersion().designFileId() != null, CreateDesignVersionErrorCode.DESIGN_FILE_REQUIRED);
        validator.validate(input.maleVersion().previewImageId() != null, CreateDesignVersionErrorCode.IMAGE_REQUIRED);
        validator.validate(input.maleVersion().designId() != null, CreateDesignVersionErrorCode.DESIGN_ID_REQUIRED);
        validator.validate(input.maleVersion().customerId() != null, CreateDesignVersionErrorCode.CUSTOMER_ID_REQUIRED);
        validator.validate(input.maleVersion().designId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
        validator.validate(input.maleVersion().customerId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected CreateDesignVersionOutput internalExecute(UseCaseValidator validator, CreateDesignVersionInput input) {
        var customerId = input.femaleVersion().customerId();
        Design firstDesign = createDesignVersionDataSource.getDesignByID(input.maleVersion().designId())
                .orElse(null);
        validator.validateAndStopExecution(firstDesign != null, CreateDesignVersionErrorCode.INVALID_DESIGN_ID);
        Account customer = createDesignVersionDataSource.getCustomerById(customerId)
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CreateDesignVersionErrorCode.INVALID_CUSTOMER_ID);
        Document firstDocument = createDesignVersionDataSource.getDocumentById(input.maleVersion().designFileId())
                .orElse(null);
        validator.validateAndStopExecution(firstDocument != null, CreateDesignVersionErrorCode.INVALID_DOCUMENT);
        Image firstImage = createDesignVersionDataSource.getImageById(input.maleVersion().previewImageId())
                .orElse(null);
        validator.validateAndStopExecution(firstImage != null, CreateDesignVersionErrorCode.INVALID_IMAGE);
        // partner
        Design secondDesign = createDesignVersionDataSource.getDesignByID(input.femaleVersion().designId())
                .orElse(null);
        validator.validateAndStopExecution(secondDesign != null, CreateDesignVersionErrorCode.INVALID_DESIGN_ID);
        Document secondDocument = createDesignVersionDataSource.getDocumentById(input.femaleVersion().designFileId())
                .orElse(null);
        validator.validateAndStopExecution(secondDocument != null, CreateDesignVersionErrorCode.INVALID_DOCUMENT);
        Image secondImage = createDesignVersionDataSource.getImageById(input.femaleVersion().previewImageId())
                .orElse(null);
        validator.validateAndStopExecution(secondImage != null, CreateDesignVersionErrorCode.INVALID_IMAGE);
        List<DesignSession> designSessions = createDesignVersionDataSource.getDesignSessionsByCustomerId(customerId);
        validator.validateAndStopExecution(designSessions != null && designSessions.size() > 0, CreateDesignVersionErrorCode.NO_MORE_SESSIONS);
        designSessions.get(0).setStatus(DesignSessionStatus.USED);
        createDesignVersionDataSource.save(designSessions.get(0));
        int firstVersionNumber = 1;
        if (firstDesign.getDesignVersions() != null && !firstDesign.getDesignVersions().isEmpty()) {
            firstVersionNumber = firstDesign.getDesignVersions().size() + 1;
        }
        DesignVersion firstDesignVersion = DesignVersion.builder()
                .designFile(firstDocument)
                .customer(customer)
                .image(firstImage)
                .design(firstDesign)
                .versionNumber(firstVersionNumber)
                .isAccepted(false)
                .isOld(false)
                .build();
        DesignVersion firstDesignVersionCreated = createDesignVersionDataSource.save(firstDesignVersion);
        int secondVersionNumber = 1;
        if (firstDesign.getId() == secondDesign.getId()) {
            secondVersionNumber = firstVersionNumber;
        }

        if (secondDesign.getDesignVersions() != null && !secondDesign.getDesignVersions().isEmpty()) {
            secondVersionNumber = secondDesign.getDesignVersions().size() + 1;
        }
        DesignVersion secondDesignVersion = DesignVersion.builder()
                .designFile(secondDocument)
                .customer(customer)
                .image(secondImage)
                .design(secondDesign)
                .versionNumber(secondVersionNumber)
                .isAccepted(false)
                .isOld(false)
                .build();
        DesignVersion secondDesignVersionCreated = createDesignVersionDataSource.save(secondDesignVersion);
        return aCreateDesignVersionMapper.toOutput(firstDesignVersionCreated, secondDesignVersionCreated);
    }
}
