package com.cplerings.core.api.payment;

import com.cplerings.core.api.payment.data.VNPayPaymentResult;
import com.cplerings.core.api.payment.mapper.APIProcessVNPayPaymentMapper;
import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.api.payment.response.VNPayPaymentResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.PaymentTag;
import com.cplerings.core.application.payment.ProcessVNPayPaymentUseCase;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.payment.output.VNPayPaymentOutput;
import com.cplerings.core.application.shared.entity.payment.APaymentStatus;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.payment.VNPayConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ProcessVNPayPaymentController extends AbstractDataController<VNPayPaymentInput, VNPayPaymentOutput, VNPayPaymentResult, VNPayPaymentRequest, VNPayPaymentResponse> {

    private final ProcessVNPayPaymentUseCase processVNPayPaymentUseCase;
    private final APIProcessVNPayPaymentMapper apiProcessVNPayPaymentMapper;

    @GetMapping(APIConstant.VNPAY_PATH)
    @PaymentTag
    @Operation(summary = "Process VNPay payment result")
    @ApiResponse(
            description = "No data response",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = NoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> process(@ModelAttribute VNPayPaymentRequest request) {
        final ResponseEntity<Object> response = handleRequest(request);
        if (response.getBody() instanceof VNPayPaymentResponse vnPayPaymentResponse) {
            return handleSuccessfulPayment(vnPayPaymentResponse, response);
        } else {
            return handleFailedPayment(response);
        }
    }

    private static ResponseEntity<Object> handleSuccessfulPayment(VNPayPaymentResponse vnPayPaymentResponse, ResponseEntity<Object> response) {
        final VNPayPaymentResult result = vnPayPaymentResponse.getData();
        if (result == null) {
            return handleFailedPayment(response);
        }
        if (result.paymentStatus() == APaymentStatus.FAILED) {
            return handleFailedPayment(response);
        }
        vnPayPaymentResponse.setRspCode(VNPayConstant.RESPONSE_CODE_SUCCESSFUL);
        vnPayPaymentResponse.setMessage("OK");
        return response;
    }

    private static ResponseEntity<Object> handleFailedPayment(ResponseEntity<Object> response) {
        final VNPayPaymentResponse vnPayPaymentResponse = VNPayPaymentResponse.builder()
                .RspCode(VNPayConstant.RESPONSE_CODE_ERROR)
                .Message("KO")
                .build();
        return ResponseEntity.badRequest().body(vnPayPaymentResponse);
    }

    @Override
    protected UseCase<VNPayPaymentInput, VNPayPaymentOutput> getUseCase() {
        return processVNPayPaymentUseCase;
    }

    @Override
    protected APIMapper<VNPayPaymentInput, VNPayPaymentOutput, VNPayPaymentResult, VNPayPaymentRequest, VNPayPaymentResponse> getMapper() {
        return apiProcessVNPayPaymentMapper;
    }
}
