package com.cplerings.core.api.verification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AccountTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.verification.mapper.APIResendVerificationMapper;
import com.cplerings.core.api.verification.request.ResendVerificationRequest;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.verification.ResendVerificationUseCase;
import com.cplerings.core.application.verification.input.ResendVerificationInput;
import com.cplerings.core.application.verification.output.ResendVerificationOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ResendVerificationController extends AbstractController<ResendVerificationInput, ResendVerificationOutput, CustomerEmailInfo, ResendVerificationRequest, CustomerEmailInfoResponse> {

    private final ResendVerificationUseCase resendVerificationUseCase;
    private final APIResendVerificationMapper apiResendVerificationMapper;

    @PostMapping(APIConstant.RESEND_VERIFICATION_CODE_PATH)
    @AccountTag
    @Operation(summary = "Resend verification code")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Resend verification code OTP",
            required = true,
            content = @Content(schema = @Schema(implementation = ResendVerificationRequest.class))
    )
    @ApiResponse(
            description = "Resend the code for verification",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CustomerEmailInfoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> resend(@RequestBody ResendVerificationRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ResendVerificationInput, ResendVerificationOutput> getUseCase() {
        return resendVerificationUseCase;
    }

    @Override
    protected APIMapper<ResendVerificationInput, ResendVerificationOutput, CustomerEmailInfo, ResendVerificationRequest, CustomerEmailInfoResponse> getMapper() {
        return apiResendVerificationMapper;
    }
}
