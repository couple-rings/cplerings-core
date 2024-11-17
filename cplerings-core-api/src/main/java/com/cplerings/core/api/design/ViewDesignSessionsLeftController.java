package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.DesignSessionLeftData;
import com.cplerings.core.api.design.mapper.APIViewDesignSessionsLeftMapper;
import com.cplerings.core.api.design.request.ViewDesignSessionsLeftRequest;
import com.cplerings.core.api.design.response.ViewDesignSessionsLeftResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewDesignSessionsLeftUseCase;
import com.cplerings.core.application.design.input.ViewDesignSessionsLeftInput;
import com.cplerings.core.application.design.output.ViewDesignSessionsLeftOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDesignSessionsLeftController extends AbstractController<ViewDesignSessionsLeftInput, ViewDesignSessionsLeftOutput, DesignSessionLeftData, ViewDesignSessionsLeftRequest, ViewDesignSessionsLeftResponse> {

    private final ViewDesignSessionsLeftUseCase viewDesignSessionsLeftUseCase;
    private final APIViewDesignSessionsLeftMapper apiViewDesignSessionsLeftMapper;

    @Override
    protected UseCase<ViewDesignSessionsLeftInput, ViewDesignSessionsLeftOutput> getUseCase() {
        return viewDesignSessionsLeftUseCase;
    }

    @Override
    protected APIMapper<ViewDesignSessionsLeftInput, ViewDesignSessionsLeftOutput, DesignSessionLeftData, ViewDesignSessionsLeftRequest, ViewDesignSessionsLeftResponse> getMapper() {
        return apiViewDesignSessionsLeftMapper;
    }

    @GetMapping(APIConstant.VIEW_DESIGN_SESSIONS_LEFT_PATH)
    @DesignTag
    @Operation(summary = "View design sessions left")
    @ApiResponse(
            description = "Design sessions left",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDesignSessionsLeftResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("customerId") Long customerId) {
        ViewDesignSessionsLeftRequest request = ViewDesignSessionsLeftRequest.builder().customerId(customerId).build();
        return handleRequest(request);
    }
}
