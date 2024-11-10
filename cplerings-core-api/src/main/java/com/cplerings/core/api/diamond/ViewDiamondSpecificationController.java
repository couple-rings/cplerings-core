package com.cplerings.core.api.diamond;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.request.ViewCustomRequestsRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.diamond.data.DiamondSpecification;
import com.cplerings.core.api.diamond.mapper.APIViewDiamondSpecificationMapper;
import com.cplerings.core.api.diamond.request.ViewDiamondSpecificationRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondSpecificationResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.DiamondSpecificationTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.diamond.ViewDiamondSpecificationUseCase;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.diamond.output.ViewDiamondSpecificationOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewDiamondSpecificationController extends AbstractController<ViewDiamondSpecificationInput, ViewDiamondSpecificationOutput, DiamondSpecification, ViewDiamondSpecificationRequest, ViewDiamondSpecificationResponse> {

    private final APIViewDiamondSpecificationMapper apiViewDiamondSpecificationMapper;
    private final ViewDiamondSpecificationUseCase viewDiamondSpecificationUseCase;

    @Override
    protected UseCase<ViewDiamondSpecificationInput, ViewDiamondSpecificationOutput> getUseCase() {
        return viewDiamondSpecificationUseCase;
    }

    @Override
    protected APIMapper<ViewDiamondSpecificationInput, ViewDiamondSpecificationOutput, DiamondSpecification, ViewDiamondSpecificationRequest, ViewDiamondSpecificationResponse> getMapper() {
        return apiViewDiamondSpecificationMapper;
    }

    @GetMapping(APIConstant.DIAMOND_SPECIFICATION_PATH)
    @DiamondSpecificationTag
    @Operation(summary = "View diamond specifications")
    @ApiResponse(
            description = "View diamond specifications",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDiamondSpecificationResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewDiamondSpecificationRequest request) {
        return handleRequest(request);
    }
}
