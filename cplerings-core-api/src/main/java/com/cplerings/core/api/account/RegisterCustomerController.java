package com.cplerings.core.api.account;

import com.cplerings.core.api.account.data.CustomerRegistration;
import com.cplerings.core.api.account.mapper.APIRegisterAccountMapper;
import com.cplerings.core.api.account.request.RegisterCustomerRequest;
import com.cplerings.core.api.account.response.CustomerRegistrationResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AccountTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.application.account.RegisterCustomerUseCase;
import com.cplerings.core.application.account.input.RegisterCustomerInput;
import com.cplerings.core.application.account.output.CustomerRegistrationOutput;
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
public class RegisterCustomerController extends AbstractDataController<RegisterCustomerInput, CustomerRegistrationOutput, CustomerRegistration, RegisterCustomerRequest, CustomerRegistrationResponse> {

    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final APIRegisterAccountMapper apiRegisterAccountMapper;

    @PostMapping(APIConstant.REGISTER_CUSTOMER_PATH)
    @AccountTag
    @Operation(summary = "Register customer account")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Customer info",
            required = true,
            content = @Content(schema = @Schema(implementation = RegisterCustomerRequest.class))
    )
    @ApiResponse(
            description = "The customer's account info for verification",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CustomerRegistrationResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> register(@RequestBody RegisterCustomerRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<RegisterCustomerInput, CustomerRegistrationOutput> getUseCase() {
        return registerCustomerUseCase;
    }

    @Override
    protected APIMapper<RegisterCustomerInput, CustomerRegistrationOutput, CustomerRegistration, RegisterCustomerRequest, CustomerRegistrationResponse> getMapper() {
        return apiRegisterAccountMapper;
    }
}
