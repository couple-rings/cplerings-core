package com.cplerings.core.api.crafting;

import com.cplerings.core.api.crafting.data.CustomOrderCraftingRequestData;
import com.cplerings.core.api.crafting.mapper.APIAcceptCraftingRequestMapper;
import com.cplerings.core.api.crafting.request.AcceptCraftingRequestRequest;
import com.cplerings.core.api.crafting.response.AcceptCraftingRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.AcceptCraftingRequestUseCase;
import com.cplerings.core.application.crafting.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.crafting.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequiredArgsConstructor
@RestController
public class AcceptCraftingRequestController extends AbstractController<AcceptCraftingRequestInput, AcceptCraftingRequestOutput, CustomOrderCraftingRequestData, AcceptCraftingRequestRequest, AcceptCraftingRequestResponse> {

    private final AcceptCraftingRequestUseCase acceptCraftingRequestUseCase;
    private final APIAcceptCraftingRequestMapper apiAcceptCraftingRequestMapper;

    @Override
    protected UseCase<AcceptCraftingRequestInput, AcceptCraftingRequestOutput> getUseCase() {
        return acceptCraftingRequestUseCase;
    }

    @Override
    protected APIMapper<AcceptCraftingRequestInput, AcceptCraftingRequestOutput, CustomOrderCraftingRequestData, AcceptCraftingRequestRequest, AcceptCraftingRequestResponse> getMapper() {
        return apiAcceptCraftingRequestMapper;
    }

    @PutMapping(APIConstant.ACCEPT_CRAFTING_REQUEST_PATH)
    @CraftingRequestTag
    @Operation(summary = "Accept or reject crafting request")
    @ApiResponse(
            description = "Custom order and crafting request information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AcceptCraftingRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody AcceptCraftingRequestRequest request) {
        return handleRequest(request);
    }
}
