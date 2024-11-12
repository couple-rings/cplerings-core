package com.cplerings.core.api.crafting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.crafting.data.CraftingRequest;
import com.cplerings.core.api.crafting.mapper.APIViewCraftingRequestMapper;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestResponse;
import com.cplerings.core.api.design.request.ViewCustomRequestRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.ViewCraftingRequestUseCase;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestInput;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCraftingRequestController extends AbstractController<ViewCraftingRequestInput, ViewCraftingRequestOutput, CraftingRequest,ViewCraftingRequestRequest, ViewCraftingRequestResponse> {

    private final ViewCraftingRequestUseCase viewCraftingRequestUseCase;
    private final APIViewCraftingRequestMapper apiViewCraftingRequestMapper;

    @Override
    protected UseCase<ViewCraftingRequestInput, ViewCraftingRequestOutput> getUseCase() {
        return viewCraftingRequestUseCase;
    }

    @Override
    protected APIMapper<ViewCraftingRequestInput, ViewCraftingRequestOutput, CraftingRequest, ViewCraftingRequestRequest, ViewCraftingRequestResponse> getMapper() {
        return apiViewCraftingRequestMapper;
    }

    @GetMapping(APIConstant.VIEW_CRAFTING_REQUEST_PATH)
    @CraftingRequestTag
    @Operation(summary = "View crafting request detail")
    @Parameter(
            name = "customRequestId",
            description = "crafting request ID"
    )
    @ApiResponse(
            description = "The crafting request information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCraftingRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("craftingRequestId") Long craftingRequestId) {
        ViewCraftingRequestRequest viewCustomRequestRequest = new ViewCraftingRequestRequest(craftingRequestId);
        return handleRequest(viewCustomRequestRequest);
    }
}
