package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.CreateDesignVersionUseCase;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.error.CreateDesignVersionErrorCode;
import com.cplerings.core.application.design.input.CreateDesignVersionInput;
import com.cplerings.core.application.design.mapper.ACreateDesignVersionMapper;
import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
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
        validator.validate(input.designFile() != null, CreateDesignVersionErrorCode.DESIGN_FILE_REQUIRED);
        validator.validate(input.previewImage() != null, CreateDesignVersionErrorCode.IMAGE_REQUIRED);
        validator.validate(input.versionNumber() > 0, CreateDesignVersionErrorCode.VERSION_NUMBER_WRONG_POSITIVE_NUMBER);
        validator.validate(input.designId() > 0, CreateDesignVersionErrorCode.DESIGN_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected CreateDesignVersionOutput internalExecute(UseCaseValidator validator, CreateDesignVersionInput input) {
        Design design = createDesignVersionDataSource.getDesignByID(input.designId())
                .orElse(null);
        validator.validateAndStopExecution(design != null, CreateDesignVersionErrorCode.INVALID_DESIGN_ID);
        Document document = Document.builder().url(input.designFile()).build();
        Image image = Image.builder().url(input.previewImage()).build();

        var documentCreated = createDesignVersionDataSource.saveDocument(document);
        var imageCreated = createDesignVersionDataSource.saveImage(image);

        DesignVersion designVersion = DesignVersion.builder()
                .designFile(documentCreated)
                .image(imageCreated)
                .design(design)
                .versionNumber(input.versionNumber())
                .isAccepted(false)
                .isOld(false)
                .build();
        DesignVersion designVersionCreated = createDesignVersionDataSource.save(designVersion);
        return aCreateDesignVersionMapper.toOutput(designVersionCreated);
    }
}
