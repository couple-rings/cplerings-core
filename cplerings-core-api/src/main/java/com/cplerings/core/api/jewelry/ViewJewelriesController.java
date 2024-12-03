package com.cplerings.core.api.jewelry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.jewelry.data.JewelriesData;
import com.cplerings.core.api.jewelry.mapper.APIViewJewelriesMapper;
import com.cplerings.core.api.jewelry.request.ViewJewelriesRequest;
import com.cplerings.core.api.jewelry.response.ViewJewelriesResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.JewelryTag;
import com.cplerings.core.application.jewelry.ViewJewelriesUseCase;
import com.cplerings.core.application.jewelry.input.ViewJewelriesInput;
import com.cplerings.core.application.jewelry.output.ViewJewelriesOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewJewelriesController extends AbstractController<ViewJewelriesInput, ViewJewelriesOutput, JewelriesData, ViewJewelriesRequest, ViewJewelriesResponse> {

    private final APIViewJewelriesMapper apiViewJewelriesMapper;
    private final ViewJewelriesUseCase viewJewelriesUseCase;

    @Override
    protected UseCase<ViewJewelriesInput, ViewJewelriesOutput> getUseCase() {
        return viewJewelriesUseCase;
    }

    @Override
    protected APIMapper<ViewJewelriesInput, ViewJewelriesOutput, JewelriesData, ViewJewelriesRequest, ViewJewelriesResponse> getMapper() {
        return apiViewJewelriesMapper;
    }

    @GetMapping(APIConstant.JEWELRIES_PATH)
    @JewelryTag
    @Operation(summary = "View jewelries")
    @ApiResponse(
            description = "Jewelries information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewJewelriesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewJewelriesRequest request) {
        return handleRequest(request);
    }
}
