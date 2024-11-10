package com.cplerings.core.api.crafting;

import com.cplerings.core.api.crafting.data.CraftingStagePaymentLinkData;
import com.cplerings.core.api.crafting.mapper.APIDepositCraftingStageMapper;
import com.cplerings.core.api.crafting.request.DepositCraftingStageRequest;
import com.cplerings.core.api.crafting.response.DepositCraftingStageResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingStageTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.DepositCraftingStageUseCase;
import com.cplerings.core.application.crafting.input.DepositCraftingStageInput;
import com.cplerings.core.application.crafting.output.DepositCraftingStageOutput;
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
public class DepositCraftingStageController extends AbstractDataController<DepositCraftingStageInput, DepositCraftingStageOutput, CraftingStagePaymentLinkData, DepositCraftingStageRequest, DepositCraftingStageResponse> {

    private final DepositCraftingStageUseCase depositCraftingStageUseCase;
    private final APIDepositCraftingStageMapper apiDepositCraftingStageMapper;

    @PostMapping(APIConstant.DEPOSIT_CRAFTING_STAGE_PATH)
    @CraftingStageTag
    @Operation(summary = "Create crafting stage payment link")
    @ApiResponse(
            description = "Crafting stage payment link",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = DepositCraftingStageResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody DepositCraftingStageRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<DepositCraftingStageInput, DepositCraftingStageOutput> getUseCase() {
        return depositCraftingStageUseCase;
    }

    @Override
    protected APIMapper<DepositCraftingStageInput, DepositCraftingStageOutput, CraftingStagePaymentLinkData, DepositCraftingStageRequest, DepositCraftingStageResponse> getMapper() {
        return apiDepositCraftingStageMapper;
    }
}
