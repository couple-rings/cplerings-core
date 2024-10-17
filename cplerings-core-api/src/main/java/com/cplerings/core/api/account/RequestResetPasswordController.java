package com.cplerings.core.api.account;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.mapper.APIRequestResetPasswordMapper;
import com.cplerings.core.api.account.request.RequestResetPasswordRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.RequestResetPasswordUseCase;
import com.cplerings.core.application.account.input.RequestResetPasswordInput;
import com.cplerings.core.application.account.output.RequestResetPasswordOutput;
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
public class RequestResetPasswordController extends AbstractDataController<RequestResetPasswordInput, RequestResetPasswordOutput, CustomerEmailInfo, RequestResetPasswordRequest, CustomerEmailInfoResponse> {

    private final RequestResetPasswordUseCase requestResetPasswordUseCase;
    private final APIRequestResetPasswordMapper apiRequestResetPasswordMapper;

    @PostMapping(APIConstant.REQUEST_RESET_PASSWORD_PATH)
    @AccountTag
    @Operation(summary = "Request password reset")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Email of account to reset password",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = RequestResetPasswordRequest.class)
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
    public ResponseEntity<Object> request(@RequestBody RequestResetPasswordRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<RequestResetPasswordInput, RequestResetPasswordOutput> getUseCase() {
        return requestResetPasswordUseCase;
    }

    @Override
    protected APIMapper<RequestResetPasswordInput, RequestResetPasswordOutput, CustomerEmailInfo, RequestResetPasswordRequest, CustomerEmailInfoResponse> getMapper() {
        return apiRequestResetPasswordMapper;
    }
}
