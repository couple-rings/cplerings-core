package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalPaymentPerOrderData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalPaymentPerOrderMapper;
import com.cplerings.core.api.dashboard.request.ViewTotalPaymentPerOrderRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalPaymentPerOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalPaymentPerOrderUseCase;
import com.cplerings.core.application.dashboard.input.ViewTotalPaymentPerOrderInput;
import com.cplerings.core.application.dashboard.output.ViewTotalPaymentPerOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTotalPaymentPerOrderController extends AbstractController<ViewTotalPaymentPerOrderInput, ViewTotalPaymentPerOrderOutput, ViewTotalPaymentPerOrderData, ViewTotalPaymentPerOrderRequest, ViewTotalPaymentPerOrderResponse> {

    private final ViewTotalPaymentPerOrderUseCase useCase;
    private final APIViewTotalPaymentPerOrderMapper mapper;

    @Override
    protected UseCase<ViewTotalPaymentPerOrderInput, ViewTotalPaymentPerOrderOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewTotalPaymentPerOrderInput, ViewTotalPaymentPerOrderOutput, ViewTotalPaymentPerOrderData, ViewTotalPaymentPerOrderRequest, ViewTotalPaymentPerOrderResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_PAYMENT_WITH_ORDER_TYPE)
    @DashboardTag
    @Operation(summary = "View total Orders")
    @ApiResponse(
            description = "The total Orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalPaymentPerOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewTotalPaymentPerOrderRequest request) {
        return handleRequest(request);
    }
}
