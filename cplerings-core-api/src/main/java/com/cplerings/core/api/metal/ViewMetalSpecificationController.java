package com.cplerings.core.api.metal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.metal.data.MetalSpecification;
import com.cplerings.core.api.metal.mapper.APIViewMetalSpecificationMapper;
import com.cplerings.core.api.metal.request.ViewMetalSpecificationRequest;
import com.cplerings.core.api.metal.response.ViewMetalSpecificationResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.MetalSpecificationTag;
import com.cplerings.core.application.metal.ViewMetalSpecificationUseCase;
import com.cplerings.core.application.metal.input.ViewMetalSpecificationInput;
import com.cplerings.core.application.metal.output.ViewMetalSpecificationOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewMetalSpecificationController extends AbstractController<ViewMetalSpecificationInput, ViewMetalSpecificationOutput, MetalSpecification, ViewMetalSpecificationRequest, ViewMetalSpecificationResponse> {

    private final ViewMetalSpecificationUseCase viewMetalSpecificationUseCase;
    private final APIViewMetalSpecificationMapper apiViewMetalSpecificationMapper;

    @Override
    protected UseCase<ViewMetalSpecificationInput, ViewMetalSpecificationOutput> getUseCase() {
        return viewMetalSpecificationUseCase;
    }

    @Override
    protected APIMapper<ViewMetalSpecificationInput, ViewMetalSpecificationOutput, MetalSpecification, ViewMetalSpecificationRequest, ViewMetalSpecificationResponse> getMapper() {
        return apiViewMetalSpecificationMapper;
    }

    @GetMapping(APIConstant.METAL_SPECIFICATION_PATH)
    @MetalSpecificationTag
    @Operation(summary = "View metal specifications")
    @ApiResponse(
            description = "View metal specifications",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewMetalSpecificationResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewMetalSpecificationRequest request) {
        return handleRequest(request);
    }
}
