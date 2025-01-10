package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.RefundData;
import com.cplerings.core.api.order.mapper.APIViewRefundMapper;
import com.cplerings.core.api.order.request.ViewRefundRequest;
import com.cplerings.core.api.order.request.ViewResellOrderRequest;
import com.cplerings.core.api.order.response.ViewRefundResponse;
import com.cplerings.core.api.order.response.ViewStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewRefundUseCase;
import com.cplerings.core.application.order.input.ViewRefundInput;
import com.cplerings.core.application.order.output.ViewRefundOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewRefundController extends AbstractController<ViewRefundInput, ViewRefundOutput, RefundData, ViewRefundRequest, ViewRefundResponse> {

    private final APIViewRefundMapper mapper;
    private final ViewRefundUseCase useCase;

    @Override
    protected UseCase<ViewRefundInput, ViewRefundOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewRefundInput, ViewRefundOutput, RefundData, ViewRefundRequest, ViewRefundResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.REFUND_PATH)
    @OrderTag
    @Operation(summary = "View resell order")
    @ApiResponse(
            description = "resell order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewRefundResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("refundOrderId") Long refundOrderId) {
        ViewRefundRequest request = ViewRefundRequest.builder().refundOrderId(refundOrderId).build();
        return handleRequest(request);
    }
}
