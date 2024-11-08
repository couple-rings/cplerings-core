package com.cplerings.core.api.craftingrequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.craftingrequest.data.CustomOrderCraftingRequestData;
import com.cplerings.core.api.craftingrequest.mapper.APIAcceptCraftingRequestMapper;
import com.cplerings.core.api.craftingrequest.request.AcceptCraftingRequestRequest;
import com.cplerings.core.api.craftingrequest.response.AcceptCraftingRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.craftingrequest.AcceptCraftingRequestUseCase;
import com.cplerings.core.application.craftingrequest.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.craftingrequest.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

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
