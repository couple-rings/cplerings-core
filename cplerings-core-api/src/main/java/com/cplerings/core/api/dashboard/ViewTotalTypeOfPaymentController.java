package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalTypeOfPaymentData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalTypeOfPaymentMapper;
import com.cplerings.core.api.dashboard.request.ViewTotalTypeOfPaymentRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalTypeOfPaymentResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalTypeOfPaymentUseCase;
import com.cplerings.core.application.dashboard.input.ViewTotalTypeOfPaymentInput;
import com.cplerings.core.application.dashboard.output.ViewTotalTypeOfPaymentOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTotalTypeOfPaymentController extends AbstractController<ViewTotalTypeOfPaymentInput, ViewTotalTypeOfPaymentOutput, ViewTotalTypeOfPaymentData, ViewTotalTypeOfPaymentRequest, ViewTotalTypeOfPaymentResponse> {

    private final ViewTotalTypeOfPaymentUseCase useCase;
    private final APIViewTotalTypeOfPaymentMapper mapper;

    @Override
    protected UseCase<ViewTotalTypeOfPaymentInput, ViewTotalTypeOfPaymentOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewTotalTypeOfPaymentInput, ViewTotalTypeOfPaymentOutput, ViewTotalTypeOfPaymentData, ViewTotalTypeOfPaymentRequest, ViewTotalTypeOfPaymentResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_PAYMENT_TYPE_BRANCH_PATH)
    @DashboardTag
    @Operation(summary = "View total Transaction")
    @ApiResponse(
            description = "The total Transaction",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalTypeOfPaymentResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewTotalTypeOfPaymentRequest request) {
        return handleRequest(request);
    }
}
