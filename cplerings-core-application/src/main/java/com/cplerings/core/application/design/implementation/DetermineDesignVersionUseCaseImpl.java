package com.cplerings.core.application.design.implementation;

import java.util.List;

import com.cplerings.core.application.design.DetermineDesignVersionUseCase;
import com.cplerings.core.application.design.datasource.DetermineDesignVersionDataSource;
import com.cplerings.core.application.design.error.DetermineDesignVersionErrorCode;
import com.cplerings.core.application.design.input.DetermineDesignVersionInput;
import com.cplerings.core.application.design.mapper.ADetermineDesignVersionMapper;
import com.cplerings.core.application.design.output.DetermineDesignVersionOutput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.DesignVersionOwner;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class DetermineDesignVersionUseCaseImpl extends AbstractUseCase<DetermineDesignVersionInput, DetermineDesignVersionOutput> implements DetermineDesignVersionUseCase {

    private final ADetermineDesignVersionMapper aDetermineDesignVersionMapper;
    private final DetermineDesignVersionDataSource determineDesignVersionDataSource;
    private final SecurityService securityService;

    @Override
    protected void validateInput(UseCaseValidator validator, DetermineDesignVersionInput input) {
        super.validateInput(validator, input);
        validator.validate(input.designVersionId() != null , DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_REQUIRED);
        validator.validate(input.designVersionId() > 0, DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
        validator.validate(input.isAccepted() != null , DetermineDesignVersionErrorCode.DESIGN_VERSION_ACCEPTED_REQUIRED);
    }

    @Override
    protected DetermineDesignVersionOutput internalExecute(UseCaseValidator validator, DetermineDesignVersionInput input) {
        DesignVersion designVersion = determineDesignVersionDataSource.getDesignVersionById(input.designVersionId())
                .orElse(null);
        validator.validateAndStopExecution(designVersion != null, DetermineDesignVersionErrorCode.INVALID_DESIGN_VERSION_ID);
        validator.validateAndStopExecution(!designVersion.getIsAccepted(), DetermineDesignVersionErrorCode.HAS_BEEN_ACCEPTED);
        if (input.isAccepted()) {
            validator.validateAndStopExecution(input.owner() != null , DetermineDesignVersionErrorCode.DESIGN_VERSION_OWNER);
            designVersion.setIsAccepted(true);
            switch (input.owner()) {
                case SELF -> designVersion.setOwner(DesignVersionOwner.SELF);
                case PARTNER -> designVersion.setOwner(DesignVersionOwner.PARTNER);
            }
            CustomRequest customRequest = designVersion.getDesign().getDesignCustomRequests().stream().findFirst().get().getCustomRequest();
            customRequest.setStatus(CustomRequestStatus.COMPLETED);
            determineDesignVersionDataSource.updateCustomRequest(customRequest);
            DesignVersion designVersionUpdated = determineDesignVersionDataSource.acceptDesignVersion(designVersion);
            return aDetermineDesignVersionMapper.toOutput(designVersionUpdated);
        }
        else {
            CurrentUser currentUser = securityService.getCurrentUser();
            int designSessionsRemaining = determineDesignVersionDataSource.getRemainingDesignSessionsCount(currentUser.id());
            if (designSessionsRemaining == 0) {
                List<DesignVersion> designVersions = determineDesignVersionDataSource.getDesignVersionByCustomerIdAndNotAcceptedAndNotOld(currentUser.id());
                designVersions.forEach(designVersionUpdate -> {
                    designVersionUpdate.setIsOld(true);
                    determineDesignVersionDataSource.save(designVersionUpdate);
                });
            }
            return DetermineDesignVersionOutput.builder().build();
        }
    }
}
