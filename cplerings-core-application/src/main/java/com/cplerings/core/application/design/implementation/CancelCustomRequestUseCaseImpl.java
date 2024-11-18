package com.cplerings.core.application.design.implementation;

import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.design.CancelCustomRequestUseCase;
import com.cplerings.core.application.design.datasource.CancelCustomRequestDataSource;
import com.cplerings.core.application.design.error.CancelCustomRequestErrorCode;
import com.cplerings.core.application.design.input.CancelCustomRequestInput;
import com.cplerings.core.application.design.mapper.ACancelCustomRequestMapper;
import com.cplerings.core.application.design.output.CancelCustomRequestOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CancelCustomRequestUseCaseImpl extends AbstractUseCase<CancelCustomRequestInput, CancelCustomRequestOutput> implements CancelCustomRequestUseCase {

    private final ACancelCustomRequestMapper aCancelCustomRequestMapper;
    private final CancelCustomRequestDataSource cancelCustomRequestDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CancelCustomRequestInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customRequestId() != null, CancelCustomRequestErrorCode.CUSTOM_REQUEST_ID_REQUIRED);
        validator.validate(input.customRequestId() > 0, CancelCustomRequestErrorCode.CUSTOM_REQUEST_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected CancelCustomRequestOutput internalExecute(UseCaseValidator validator, CancelCustomRequestInput input) {
        CustomRequest customRequest = cancelCustomRequestDataSource.getCustomRequestById(input.customRequestId())
                .orElse(null);
        validator.validateAndStopExecution(customRequest != null, CancelCustomRequestErrorCode.INVALID_CUSTOM_REQUEST_ID);
        List<Design> designs = new ArrayList<>();
        customRequest.getDesignCustomRequests().forEach(designCustomRequest -> {
            Design design = designCustomRequest.getDesign();
            design.setStatus(DesignStatus.AVAILABLE);
            cancelCustomRequestDataSource.save(design);
            designs.add(design);
        });
        if (designs != null && !designs.isEmpty()) {
            List<DesignVersion> firstDesignVersions = cancelCustomRequestDataSource.getDesignVersionsByDesignId(designs.get(0).getId());
            firstDesignVersions.forEach(designVersion -> {
                designVersion.setIsOld(true);
                cancelCustomRequestDataSource.save(designVersion);
            });

            List<DesignVersion> secondDesignVersions = cancelCustomRequestDataSource.getDesignVersionsByDesignId(designs.get(1).getId());
            secondDesignVersions.forEach(designVersion -> {
                designVersion.setIsOld(true);
                cancelCustomRequestDataSource.save(designVersion);
            });
        }
        customRequest.setStatus(CustomRequestStatus.REJECTED);
        CustomRequest customRequestUpdated = cancelCustomRequestDataSource.save(customRequest);
        return aCancelCustomRequestMapper.toOutput(customRequestUpdated);
    }
}
