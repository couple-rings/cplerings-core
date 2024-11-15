package com.cplerings.core.api.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.JewelersData;
import com.cplerings.core.api.account.mapper.APIViewJewelersMapper;
import com.cplerings.core.api.account.request.ViewJewelersRequest;
import com.cplerings.core.api.account.response.ViewJewelersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ViewJewelersUseCase;
import com.cplerings.core.application.account.input.ViewJewelersInput;
import com.cplerings.core.application.account.output.ViewJewelersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewJewelersController extends AbstractController<ViewJewelersInput, ViewJewelersOutput, JewelersData, ViewJewelersRequest, ViewJewelersResponse> {

    private final APIViewJewelersMapper viewJewelersMapper;
    private final ViewJewelersUseCase viewJewelersUseCase;

    @Override
    protected UseCase<ViewJewelersInput, ViewJewelersOutput> getUseCase() {
        return viewJewelersUseCase;
    }

    @Override
    protected APIMapper<ViewJewelersInput, ViewJewelersOutput, JewelersData, ViewJewelersRequest, ViewJewelersResponse> getMapper() {
        return viewJewelersMapper;
    }

    @GetMapping(APIConstant.JEWELERS_PATH)
    @AccountTag
    @Operation(summary = "View jewelers of a branch")
    @ApiResponse(
            description = "The jewelers",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewJewelersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewJewelersRequest request) {
        return handleRequest(request);
    }
}
