package com.cplerings.core.api.jewelry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.jewelry.data.JewelryCategoriesData;
import com.cplerings.core.api.jewelry.mapper.APIViewJewelryCategoriesMapper;
import com.cplerings.core.api.jewelry.request.ViewJewelryCategoriesRequest;
import com.cplerings.core.api.jewelry.response.ViewJewelryCategoriesResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.JewelryTag;
import com.cplerings.core.application.jewelry.ViewJewelryCategoriesUseCase;
import com.cplerings.core.application.jewelry.input.ViewJewelryCategoriesInput;
import com.cplerings.core.application.jewelry.output.ViewJewelryCategoriesOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewJewelryCategoriesController extends AbstractController<ViewJewelryCategoriesInput, ViewJewelryCategoriesOutput, JewelryCategoriesData, ViewJewelryCategoriesRequest, ViewJewelryCategoriesResponse> {

    private final APIViewJewelryCategoriesMapper apiViewJewelryCategoriesMapper;
    private final ViewJewelryCategoriesUseCase viewJewelryCategoriesUseCase;

    @Override
    protected UseCase<ViewJewelryCategoriesInput, ViewJewelryCategoriesOutput> getUseCase() {
        return viewJewelryCategoriesUseCase;
    }

    @Override
    protected APIMapper<ViewJewelryCategoriesInput, ViewJewelryCategoriesOutput, JewelryCategoriesData, ViewJewelryCategoriesRequest, ViewJewelryCategoriesResponse> getMapper() {
        return apiViewJewelryCategoriesMapper;
    }

    @GetMapping(APIConstant.JEWELRIES_CATEGORIES_PATH)
    @JewelryTag
    @Operation(summary = "View jewelry categories")
    @ApiResponse(
            description = "jewelry categories information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewJewelryCategoriesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewJewelryCategoriesRequest request) {
        return handleRequest(request);
    }
}
