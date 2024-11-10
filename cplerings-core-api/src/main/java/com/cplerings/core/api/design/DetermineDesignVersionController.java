package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.mapper.APIDetermineDesignVersionMapper;
import com.cplerings.core.api.design.request.DetermineDesignVersionRequest;
import com.cplerings.core.api.design.response.DetermineDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.DetermineDesignVersionUseCase;
import com.cplerings.core.application.design.input.DetermineDesignVersionInput;
import com.cplerings.core.application.design.output.DetermineDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class DetermineDesignVersionController extends AbstractController<DetermineDesignVersionInput, DetermineDesignVersionOutput, DesignVersion, DetermineDesignVersionRequest, DetermineDesignVersionResponse> {

    private final APIDetermineDesignVersionMapper apiDetermineDesignVersionMapper;
    private final DetermineDesignVersionUseCase determineDesignVersionUseCase;

    @PutMapping(APIConstant.ACCEPT_SINGLE_DESIGN_VERSION_PATH)
    @DesignTag
    @Operation(summary = "Accept Design version")
    @ApiResponse(
            description = "Accept version information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = DetermineDesignVersionResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@PathVariable("designVersionId") Long designVersionId) {
        DetermineDesignVersionRequest request = new DetermineDesignVersionRequest(designVersionId);
        return handleRequest(request);
    }

    @Override
    protected UseCase<DetermineDesignVersionInput, DetermineDesignVersionOutput> getUseCase() {
        return determineDesignVersionUseCase;
    }

    @Override
    protected APIMapper<DetermineDesignVersionInput, DetermineDesignVersionOutput, DesignVersion, DetermineDesignVersionRequest, DetermineDesignVersionResponse> getMapper() {
        return apiDetermineDesignVersionMapper;
    }
}
