package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.mapper.APIUpdateDesignVersionStatusMapper;
import com.cplerings.core.api.design.request.UpdateDesignVersionStatusRequest;
import com.cplerings.core.api.design.response.UpdateDesignVersionStatusResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.UpdateDesignVersionStatusUseCase;
import com.cplerings.core.application.design.input.UpdateDesignVersionStatusInput;
import com.cplerings.core.application.design.output.UpdateDesignVersionStatusOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UpdateDesignVersionStatusController extends AbstractController<UpdateDesignVersionStatusInput, UpdateDesignVersionStatusOutput, DesignVersion, UpdateDesignVersionStatusRequest, UpdateDesignVersionStatusResponse> {

    private final APIUpdateDesignVersionStatusMapper apiUpdateDesignVersionStatusMapper;
    private final UpdateDesignVersionStatusUseCase updateDesignVersionStatusUseCase;

    @PutMapping(APIConstant.ACCEPT_SINGLE_DESIGN_VERSION_PATH)
    @DesignTag
    @Operation(summary = "Accept Design version")
    @ApiResponse(
            description = "Accept version information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateDesignVersionStatusResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@PathVariable("designVersionId") Long designVersionId) {
        UpdateDesignVersionStatusRequest request = new UpdateDesignVersionStatusRequest(designVersionId);
        return handleRequest(request);
    }

    @Override
    protected UseCase<UpdateDesignVersionStatusInput, UpdateDesignVersionStatusOutput> getUseCase() {
        return updateDesignVersionStatusUseCase;
    }

    @Override
    protected APIMapper<UpdateDesignVersionStatusInput, UpdateDesignVersionStatusOutput, DesignVersion, UpdateDesignVersionStatusRequest, UpdateDesignVersionStatusResponse> getMapper() {
        return apiUpdateDesignVersionStatusMapper;
    }
}
