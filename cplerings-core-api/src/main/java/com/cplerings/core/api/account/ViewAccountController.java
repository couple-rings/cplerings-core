package com.cplerings.core.api.account;

import com.cplerings.core.api.account.data.AccountData;
import com.cplerings.core.api.account.mapper.APIViewAccountMapper;
import com.cplerings.core.api.account.request.ViewAccountRequest;
import com.cplerings.core.api.account.response.AccountResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ViewAccountUseCase;
import com.cplerings.core.application.account.input.ViewAccountInput;
import com.cplerings.core.application.account.output.AccountOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ViewAccountController extends AbstractDataController<ViewAccountInput, AccountOutput, AccountData, ViewAccountRequest, AccountResponse> {

    private final ViewAccountUseCase viewAccountUseCase;
    private final APIViewAccountMapper apiViewAccountMapper;

    @GetMapping(APIConstant.ACCOUNT_PATH)
    @AccountTag
    @Operation(summary = "View account detail")
    @Parameter(
            name = "id",
            description = "Account ID",
            in = ParameterIn.PATH,
            required = true
    )
    @ApiResponse(
            description = "The account detail",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AccountResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("id") Long id) {
        ViewAccountRequest viewAccountRequest = new ViewAccountRequest(id);
        return handleRequest(viewAccountRequest);
    }

    @Override
    protected UseCase<ViewAccountInput, AccountOutput> getUseCase() {
        return viewAccountUseCase;
    }

    @Override
    protected APIMapper<ViewAccountInput, AccountOutput, AccountData, ViewAccountRequest, AccountResponse> getMapper() {
        return apiViewAccountMapper;
    }
}
