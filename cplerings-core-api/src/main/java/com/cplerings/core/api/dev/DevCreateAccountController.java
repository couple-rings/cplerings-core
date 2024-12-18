package com.cplerings.core.api.dev;

import com.cplerings.core.api.dev.mapper.APIDevCreateAccountMapper;
import com.cplerings.core.api.dev.request.DevCreateAccountRequest;
import com.cplerings.core.api.dev.response.DevCreateAccountResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DevTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.DevCreateAccountUseCase;
import com.cplerings.core.application.account.input.DevCreateAccountInput;
import com.cplerings.core.application.account.output.DevCreateAccountOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class DevCreateAccountController extends AbstractDataController<DevCreateAccountInput, DevCreateAccountOutput, AAccount, DevCreateAccountRequest, DevCreateAccountResponse> {

    private final DevCreateAccountUseCase useCase;
    private final APIDevCreateAccountMapper mapper;

    @Override
    protected UseCase<DevCreateAccountInput, DevCreateAccountOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<DevCreateAccountInput, DevCreateAccountOutput, AAccount, DevCreateAccountRequest, DevCreateAccountResponse> getMapper() {
        return mapper;
    }

    @PostMapping(APIConstant.DEV_ACCOUNTS_PATH)
    @DevTag
    @Operation(summary = "Create Account - DEV only!")
    @ApiResponse(
            description = "The created Account",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = DevCreateAccountResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> process(@ModelAttribute DevCreateAccountRequest request) {
        return handleRequest(request);
    }
}
