package com.cplerings.core.api.crafting;

import com.cplerings.core.api.crafting.mapper.APICompleteCraftingStageMapper;
import com.cplerings.core.api.crafting.request.CompleteCraftingStageRequest;
import com.cplerings.core.api.crafting.response.CompleteCraftingStageResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingStageTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.CompleteCraftingStageUseCase;
import com.cplerings.core.application.crafting.input.CompleteCraftingStageInput;
import com.cplerings.core.application.crafting.output.CompleteCraftingStageOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class CompleteCraftingStageController extends AbstractDataController<CompleteCraftingStageInput, CompleteCraftingStageOutput, ACraftingStage, CompleteCraftingStageRequest, CompleteCraftingStageResponse> {

    private final CompleteCraftingStageUseCase completeCraftingStageUseCase;
    private final APICompleteCraftingStageMapper apiCompleteCraftingStageMapper;

    @Override
    protected UseCase<CompleteCraftingStageInput, CompleteCraftingStageOutput> getUseCase() {
        return completeCraftingStageUseCase;
    }

    @Override
    protected APIMapper<CompleteCraftingStageInput, CompleteCraftingStageOutput, ACraftingStage, CompleteCraftingStageRequest, CompleteCraftingStageResponse> getMapper() {
        return apiCompleteCraftingStageMapper;
    }

    @PostMapping(APIConstant.COMPLETE_CRAFTING_STAGE_PATH)
    @CraftingStageTag
    @Operation(summary = "Complete crafting stage")
    @ApiResponse(
            description = "Crafting stage info",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CompleteCraftingStageResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@PathVariable(name = "craftingStageId") Long craftingStageId,
                                         @RequestBody CompleteCraftingStageRequest request) {
        request.setCraftingStageId(craftingStageId);
        return handleRequest(request);
    }
}
