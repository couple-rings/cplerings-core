package com.cplerings.core.api.jewelry;

import com.cplerings.core.api.jewelry.mapper.APIResellJewelryMapper;
import com.cplerings.core.api.jewelry.request.ResellJewelryRequest;
import com.cplerings.core.api.jewelry.response.ResellJewelryResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.JewelryTag;
import com.cplerings.core.application.jewelry.ResellJewelryUseCase;
import com.cplerings.core.application.jewelry.input.ResellJewelryInput;
import com.cplerings.core.application.jewelry.output.ResellJewelryOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.AccessLevel;
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
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ResellJewelryController extends AbstractDataController<ResellJewelryInput, ResellJewelryOutput, AResellOrder, ResellJewelryRequest, ResellJewelryResponse> {

    private final ResellJewelryUseCase useCase;
    private final APIResellJewelryMapper apiMapper;

    @Override
    protected UseCase<ResellJewelryInput, ResellJewelryOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ResellJewelryInput, ResellJewelryOutput, AResellOrder, ResellJewelryRequest, ResellJewelryResponse> getMapper() {
        return apiMapper;
    }

    @PostMapping(APIConstant.RESELL_JEWELRY_PATH)
    @JewelryTag
    @Operation(summary = "Resell jewelry")
    @ApiResponse(
            description = "jewelry information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ResellJewelryResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@PathVariable("jewelryId") Long jewelryId,
                                         @RequestBody ResellJewelryRequest request) {
        request.setJewelryId(jewelryId);
        return handleRequest(request);
    }
}
