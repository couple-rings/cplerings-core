package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.UpdateDesignVersionStatusUseCase;
import com.cplerings.core.application.design.datasource.UpdateDesignVersionStatusDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignVersionDataSource;
import com.cplerings.core.application.design.input.UpdateDesignVersionStatusInput;
import com.cplerings.core.application.design.mapper.AUpdateDesignVersionStatusMapper;
import com.cplerings.core.application.design.output.UpdateDesignVersionStatusOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.DesignVersion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class UpdateDesignVersionStatusUseCaseImpl extends AbstractUseCase<UpdateDesignVersionStatusInput, UpdateDesignVersionStatusOutput> implements UpdateDesignVersionStatusUseCase {

    private final AUpdateDesignVersionStatusMapper aUpdateDesignVersionStatusMapper;
    private final UpdateDesignVersionStatusDataSource updateDesignVersionStatusDataSource;
    private final ViewDesignVersionDataSource viewDesignVersionDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, UpdateDesignVersionStatusInput input) {
        super.validateInput(validator, input);
        if (input.designVersionId() != null) {
//            validator.validate(input.designVersionId() > 0, DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
        }
    }

    @Override
    protected UpdateDesignVersionStatusOutput internalExecute(UseCaseValidator validator, UpdateDesignVersionStatusInput input) {
        DesignVersion designVersion = viewDesignVersionDataSource.getDesignVersionById(input.designVersionId())
                .orElse(null);
//        validator.validateAndStopExecution(designVersion != null, INVALID_DESIGN_VERSION_ID);
//        designVersion.setAccepted(true);
        DesignVersion designVersionUpdated = updateDesignVersionStatusDataSource.acceptDesignVersion(designVersion);
        return aUpdateDesignVersionStatusMapper.toOutput(designVersionUpdated);
    }
}
