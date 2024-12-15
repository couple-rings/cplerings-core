package com.cplerings.core.api.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.CustomersData;
import com.cplerings.core.api.account.mapper.APIViewCustomersMapper;
import com.cplerings.core.api.account.request.ViewCustomersRequest;
import com.cplerings.core.api.account.response.ViewCustomersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ViewCustomersUseCase;
import com.cplerings.core.application.account.input.ViewCustomersInput;
import com.cplerings.core.application.account.output.ViewCustomersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCustomersController extends AbstractController<ViewCustomersInput, ViewCustomersOutput, CustomersData, ViewCustomersRequest, ViewCustomersResponse> {

    private final ViewCustomersUseCase viewCustomersUseCase;
    private final APIViewCustomersMapper apiViewCustomersMapper;

    @Override
    protected UseCase<ViewCustomersInput, ViewCustomersOutput> getUseCase() {
        return viewCustomersUseCase;
    }

    @Override
    protected APIMapper<ViewCustomersInput, ViewCustomersOutput, CustomersData, ViewCustomersRequest, ViewCustomersResponse> getMapper() {
        return apiViewCustomersMapper;
    }

    @GetMapping(APIConstant.CUSTOMERS_PATH)
    @AccountTag
    @Operation(summary = "View customers of a branch")
    @ApiResponse(
            description = "The customers information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCustomersRequest request) {
        return handleRequest(request);
    }
}
