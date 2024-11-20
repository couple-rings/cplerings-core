package com.cplerings.core.application.design.implementation;

import java.util.List;

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
import com.cplerings.core.domain.design.DesignVersionOwner;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class DetermineDesignVersionUseCaseImpl extends AbstractUseCase<DetermineDesignVersionInput, DetermineDesignVersionOutput> implements DetermineDesignVersionUseCase {

    private final ADetermineDesignVersionMapper aDetermineDesignVersionMapper;
    private final DetermineDesignVersionDataSource determineDesignVersionDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, DetermineDesignVersionInput input) {
        super.validateInput(validator, input);
        validator.validate(input.femaleVersion().designVersionId() != null, DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_REQUIRED);
        validator.validate(input.maleVersion().designVersionId() != null, DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_REQUIRED);
        validator.validate(input.femaleVersion().designVersionId() > 0, DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
        validator.validate(input.maleVersion().designVersionId() > 0, DetermineDesignVersionErrorCode.DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected DetermineDesignVersionOutput internalExecute(UseCaseValidator validator, DetermineDesignVersionInput input) {
        DesignVersion femaleDesignVersion = determineDesignVersionDataSource.getDesignVersionById(input.femaleVersion().designVersionId())
                .orElse(null);
        validator.validateAndStopExecution(femaleDesignVersion != null, DetermineDesignVersionErrorCode.INVALID_DESIGN_VERSION_ID);
        DesignVersion maleDesignVersion = determineDesignVersionDataSource.getDesignVersionById(input.maleVersion().designVersionId())
                .orElse(null);
        validator.validateAndStopExecution(maleDesignVersion != null, DetermineDesignVersionErrorCode.INVALID_DESIGN_VERSION_ID);
        validator.validateAndStopExecution(!femaleDesignVersion.getIsAccepted(), DetermineDesignVersionErrorCode.HAS_BEEN_ACCEPTED);
        validator.validateAndStopExecution(!maleDesignVersion.getIsAccepted(), DetermineDesignVersionErrorCode.HAS_BEEN_ACCEPTED);
        validator.validateAndStopExecution(input.femaleVersion().owner() != null, DetermineDesignVersionErrorCode.DESIGN_VERSION_OWNER);
        validator.validateAndStopExecution(input.maleVersion().owner() != null, DetermineDesignVersionErrorCode.DESIGN_VERSION_OWNER);
        femaleDesignVersion.setIsAccepted(true);
        maleDesignVersion.setIsAccepted(true);
        switch (input.femaleVersion().owner()) {
            case SELF -> femaleDesignVersion.setOwner(DesignVersionOwner.SELF);
            case PARTNER -> femaleDesignVersion.setOwner(DesignVersionOwner.PARTNER);
        }

        switch (input.maleVersion().owner()) {
            case SELF -> maleDesignVersion.setOwner(DesignVersionOwner.SELF);
            case PARTNER -> maleDesignVersion.setOwner(DesignVersionOwner.PARTNER);
        }
        DesignVersion femaleDesignVersionUpdated = determineDesignVersionDataSource.acceptDesignVersion(femaleDesignVersion);
        DesignVersion maleDesignVersionUpdated = determineDesignVersionDataSource.acceptDesignVersion(maleDesignVersion);
        List<DesignVersion> femaleDesignVersions = determineDesignVersionDataSource.getDesignVersionRemainingByDesignId(femaleDesignVersion.getDesign().getId() ,femaleDesignVersionUpdated.getId());
        femaleDesignVersions.forEach(designVersionUpdate -> {
            designVersionUpdate.setIsOld(true);
            determineDesignVersionDataSource.save(designVersionUpdate);
        });
        if (!femaleDesignVersionUpdated.getDesign().getId().equals(maleDesignVersion.getDesign().getId())) {
            List<DesignVersion> maleDesignVersions = determineDesignVersionDataSource.getDesignVersionRemainingByDesignId(maleDesignVersion.getDesign().getId() ,maleDesignVersionUpdated.getId());
            maleDesignVersions.forEach(designVersionUpdate -> {
                designVersionUpdate.setIsOld(true);
                determineDesignVersionDataSource.save(designVersionUpdate);
            });
        }
        Long customerId = femaleDesignVersion.getDesign().getId() == null ? maleDesignVersion.getDesign().getId() : femaleDesignVersion.getDesign().getId();
        List<DesignSession> designSessions = determineDesignVersionDataSource.getListDesignSession(customerId);
        designSessions.forEach(designSession -> {
            designSession.setStatus(DesignSessionStatus.USED);
            determineDesignVersionDataSource.save(designSession);
        });
        return aDetermineDesignVersionMapper.toOutput(femaleDesignVersionUpdated, maleDesignVersionUpdated);
    }
}
