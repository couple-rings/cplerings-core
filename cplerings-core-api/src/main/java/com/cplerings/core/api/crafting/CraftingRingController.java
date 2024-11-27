package com.cplerings.core.api.crafting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.crafting.mapper.APICraftingRingMapper;
import com.cplerings.core.api.crafting.request.CraftingRingRequest;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.CraftingRingUseCase;
import com.cplerings.core.application.crafting.input.CraftingRingInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CraftingRingController extends AbstractController<CraftingRingInput, NoOutput, NoData, CraftingRingRequest, NoResponse> {

    private final APICraftingRingMapper apiCraftingRingMapper;
    private final CraftingRingUseCase craftingRingUseCase;

    @Override
    protected UseCase<CraftingRingInput, NoOutput> getUseCase() {
        return craftingRingUseCase;
    }

    @Override
    protected APIMapper<CraftingRingInput, NoOutput, NoData, CraftingRingRequest, NoResponse> getMapper() {
        return apiCraftingRingMapper;
    }

    @PostMapping(APIConstant.CRAFTING_RING_PATH)
    @CraftingRequestTag
    @Operation(summary = "Create crafting ring")
    @ApiResponse(
            description = "No response",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = NoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CraftingRingRequest request) {
        return handleRequest(request);
    }
}
