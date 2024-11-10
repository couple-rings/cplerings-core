package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.DetermineDesignVersionUseCase;
import com.cplerings.core.application.design.datasource.DetermineDesignVersionDataSource;
import com.cplerings.core.application.design.error.DetermineDesignVersionErrorCode;
import com.cplerings.core.application.design.input.DetermineDesignVersionInput;
import com.cplerings.core.application.design.mapper.ADetermineDesignVersionMapper;
import com.cplerings.core.application.design.output.DetermineDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class DetermineDesignVersionUseCaseImpl extends AbstractUseCase<DetermineDesignVersionInput, DetermineDesignVersionOutput> implements DetermineDesignVersionUseCase {

    private final ADetermineDesignVersionMapper aDetermineDesignVersionMapper;
    private final DetermineDesignVersionDataSource determineDesignVersionDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, DetermineDesignVersionInput input) {
        super.validateInput(validator, input);
        validator.validate(input.designVersionId() != null , DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_REQUIRED);
        validator.validate(input.designVersionId() > 0, DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected DetermineDesignVersionOutput internalExecute(UseCaseValidator validator, DetermineDesignVersionInput input) {
        DesignVersion designVersion = determineDesignVersionDataSource.getDesignVersionById(input.designVersionId())
                .orElse(null);
        validator.validateAndStopExecution(designVersion != null, DetermineDesignVersionErrorCode.INVALID_DESIGN_VERSION_ID);
        validator.validateAndStopExecution(!designVersion.getIsAccepted(), DetermineDesignVersionErrorCode.HAS_BEEN_ACCEPTED);
        designVersion.setIsAccepted(true);
        CustomRequest customRequest = designVersion.getDesign().getDesignCustomRequests().stream().findFirst().get().getCustomRequest();
        customRequest.setStatus(CustomRequestStatus.COMPLETED);
        determineDesignVersionDataSource.updateCustomRequest(customRequest);
        DesignVersion designVersionUpdated = determineDesignVersionDataSource.acceptDesignVersion(designVersion);
        return aDetermineDesignVersionMapper.toOutput(designVersionUpdated);
    }
}
