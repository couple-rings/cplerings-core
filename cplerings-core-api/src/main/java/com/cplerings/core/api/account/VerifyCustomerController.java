package com.cplerings.core.api.account;

import com.cplerings.core.api.account.mapper.APIVerifyCustomerMapper;
import com.cplerings.core.api.account.request.VerifyCustomerRequest;
import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AccountTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.application.account.VerifyCustomerUseCase;
import com.cplerings.core.application.account.input.VerifyCustomerInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
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
public class VerifyCustomerController extends AbstractDataController<VerifyCustomerInput, AuthenticationTokenOutput, AuthenticationToken, VerifyCustomerRequest, AuthenticationTokenResponse> {

    private final VerifyCustomerUseCase verifyCustomerUseCase;
    private final APIVerifyCustomerMapper apiVerifyCustomerMapper;

    @PostMapping(APIConstant.VERIFY_CUSTOMER_PATH)
    @AccountTag
    @Operation(summary = "Verify customer account")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Verification detail",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = VerifyCustomerRequest.class)
            )
    )
    @ApiResponse(
            description = "The authentication token",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AuthenticationTokenResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> verify(@RequestBody VerifyCustomerRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<VerifyCustomerInput, AuthenticationTokenOutput> getUseCase() {
        return verifyCustomerUseCase;
    }

    @Override
    protected APIMapper<VerifyCustomerInput, AuthenticationTokenOutput, AuthenticationToken, VerifyCustomerRequest, AuthenticationTokenResponse> getMapper() {
        return apiVerifyCustomerMapper;
    }
}
