package com.cplerings.core.api.dev;

import com.cplerings.core.api.dev.mapper.APIGenerateVNPayPaymentLinkMapper;
import com.cplerings.core.api.dev.request.GenerateVNPayPaymentLinkRequest;
import com.cplerings.core.api.dev.response.GenerateVNPayPaymentLinkResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DevTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dev.GenerateVNPayPaymentLinkUseCase;
import com.cplerings.core.application.dev.input.GenerateVNPayPaymentLinkInput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

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
public class GenerateVNPayPaymentLinkController extends AbstractDataController<GenerateVNPayPaymentLinkInput, String, String, GenerateVNPayPaymentLinkRequest, GenerateVNPayPaymentLinkResponse> {

    private final GenerateVNPayPaymentLinkUseCase generateVNPayPaymentLinkUseCase;
    private final APIGenerateVNPayPaymentLinkMapper apiGenerateVNPayPaymentLinkMapper;

    @GetMapping(APIConstant.DEV_VNPAY_PATH)
    @DevTag
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
    public ResponseEntity<Object> process(@ModelAttribute GenerateVNPayPaymentLinkRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<GenerateVNPayPaymentLinkInput, String> getUseCase() {
        return generateVNPayPaymentLinkUseCase;
    }

    @Override
    protected APIMapper<GenerateVNPayPaymentLinkInput, String, String, GenerateVNPayPaymentLinkRequest, GenerateVNPayPaymentLinkResponse> getMapper() {
        return apiGenerateVNPayPaymentLinkMapper;
    }
}
