package com.cplerings.core.api.account;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.mapper.APIResendCustomerVerificationCodeMapper;
import com.cplerings.core.api.account.request.ResendCustomerVerificationCodeRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AccountTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.application.account.ResendCustomerVerificationCodeUseCase;
import com.cplerings.core.application.account.input.ResendCustomerVerificationCodeInput;
import com.cplerings.core.application.account.output.ResendCustomerVerificationCodeOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ResendCustomerVerificationCodeController extends AbstractController<ResendCustomerVerificationCodeInput, ResendCustomerVerificationCodeOutput, CustomerEmailInfo, ResendCustomerVerificationCodeRequest, CustomerEmailInfoResponse> {

    private final ResendCustomerVerificationCodeUseCase resendCustomerVerificationCodeUseCase;
    private final APIResendCustomerVerificationCodeMapper apiResendCustomerVerificationCodeMapper;

    @PostMapping(APIConstant.RESEND_CUSTOMER_VERIFICATION_CODE_PATH)
    @AccountTag
    @Operation(summary = "Resend verification code")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Customer into to resend verification code",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ResendCustomerVerificationCodeRequest.class)
            )
    )
    @ApiResponse(
            description = "The requester's email",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CustomerEmailInfoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> resend(@RequestBody ResendCustomerVerificationCodeRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ResendCustomerVerificationCodeInput, ResendCustomerVerificationCodeOutput> getUseCase() {
        return resendCustomerVerificationCodeUseCase;
    }

    @Override
    protected APIMapper<ResendCustomerVerificationCodeInput, ResendCustomerVerificationCodeOutput, CustomerEmailInfo, ResendCustomerVerificationCodeRequest, CustomerEmailInfoResponse> getMapper() {
        return apiResendCustomerVerificationCodeMapper;
    }
}
