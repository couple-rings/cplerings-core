package com.cplerings.core.api.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.request.ViewCustomDesignRequest;
import com.cplerings.core.api.design.response.ViewCustomDesignResponse;
import com.cplerings.core.api.payment.mapper.APIGetPaymentMapper;
import com.cplerings.core.api.payment.request.GetPaymentRequest;
import com.cplerings.core.api.payment.response.GetPaymentResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.PaymentTag;
import com.cplerings.core.application.payment.GetPaymentUseCase;
import com.cplerings.core.application.payment.input.GetPaymentInput;
import com.cplerings.core.application.payment.output.GetPaymentOutput;
import com.cplerings.core.application.shared.entity.payment.APayment;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetPaymentController extends AbstractController<GetPaymentInput, GetPaymentOutput, APayment, GetPaymentRequest, GetPaymentResponse> {

    private final APIGetPaymentMapper apiGetPaymentMapper;
    private final GetPaymentUseCase getPaymentUseCase;

    @Override
    protected UseCase<GetPaymentInput, GetPaymentOutput> getUseCase() {
        return getPaymentUseCase;
    }

    @Override
    protected APIMapper<GetPaymentInput, GetPaymentOutput, APayment, GetPaymentRequest, GetPaymentResponse> getMapper() {
        return apiGetPaymentMapper;
    }

    @GetMapping(APIConstant.PAYMENT_PATH)
    @PaymentTag
    @Operation(summary = "View a payment")
    @ApiResponse(
            description = "Payment infor",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetPaymentResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("paymentId") Long paymentId) {
        GetPaymentRequest request = GetPaymentRequest.builder().paymentId(paymentId).build();
        return handleRequest(request);
    }
}
