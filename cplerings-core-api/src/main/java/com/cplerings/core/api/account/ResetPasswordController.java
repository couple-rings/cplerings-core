package com.cplerings.core.api.account;

import com.cplerings.core.api.account.mapper.APIResetPasswordMapper;
import com.cplerings.core.api.account.request.ResetPasswordRequest;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ResetPasswordUseCase;
import com.cplerings.core.application.account.input.ResetPasswordInput;
import com.cplerings.core.application.shared.output.NoOutput;
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
public class ResetPasswordController extends AbstractDataController<ResetPasswordInput, NoOutput, NoData, ResetPasswordRequest, NoResponse> {

    private final ResetPasswordUseCase resetPasswordUseCase;
    private final APIResetPasswordMapper apiResetPasswordMapper;

    @PostMapping(APIConstant.RESET_PASSWORD_PATH)
    @AccountTag
    @Operation(summary = "Reset customer password with reset OTP")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Reset password detail",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ResetPasswordRequest.class)
            )
    )
    @ApiResponse(
            description = "No data",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = NoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> reset(@RequestBody ResetPasswordRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ResetPasswordInput, NoOutput> getUseCase() {
        return resetPasswordUseCase;
    }

    @Override
    protected APIMapper<ResetPasswordInput, NoOutput, NoData, ResetPasswordRequest, NoResponse> getMapper() {
        return apiResetPasswordMapper;
    }
}
