package com.cplerings.core.api.craftingrequest;

import com.cplerings.core.api.craftingrequest.data.CraftingRequest;
import com.cplerings.core.api.craftingrequest.mapper.APICreateCraftingRequestMapper;
import com.cplerings.core.api.craftingrequest.request.CreateCraftingRequestRequest;
import com.cplerings.core.api.craftingrequest.response.CreateCraftingRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.craftingrequest.CreateCraftingRequestUseCase;
import com.cplerings.core.application.craftingrequest.input.CreateCraftingRequestInput;
import com.cplerings.core.application.craftingrequest.output.CreateCraftingRequestOutput;
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

@RequiredArgsConstructor
@RestController
public class CreateCraftingRequestController extends AbstractController<CreateCraftingRequestInput, CreateCraftingRequestOutput, CraftingRequest, CreateCraftingRequestRequest, CreateCraftingRequestResponse> {

    private final APICreateCraftingRequestMapper apiCreateCraftingRequestMapper;
    private final CreateCraftingRequestUseCase createCraftingRequestUseCase;

    @Override
    protected UseCase<CreateCraftingRequestInput, CreateCraftingRequestOutput> getUseCase() {
        return createCraftingRequestUseCase;
    }

    @Override
    protected APIMapper<CreateCraftingRequestInput, CreateCraftingRequestOutput, CraftingRequest, CreateCraftingRequestRequest, CreateCraftingRequestResponse> getMapper() {
        return apiCreateCraftingRequestMapper;
    }

    @PostMapping(APIConstant.CRAFTING_REQUEST_PATH)
    @CraftingRequestTag
    @Operation(summary = "Create crafting request")
    @ApiResponse(
            description = "Crafting request information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateCraftingRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateCraftingRequestRequest request) {
        return handleRequest(request);
    }
}
