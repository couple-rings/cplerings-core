package com.cplerings.core.api.jewelry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.jewelry.mapper.APIUpdateJewelryMapper;
import com.cplerings.core.api.jewelry.request.UpdateJewelryRequest;
import com.cplerings.core.api.jewelry.request.data.UpdateJewelryRequestData;
import com.cplerings.core.api.jewelry.response.UpdateJewelryResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.JewelryTag;
import com.cplerings.core.application.jewelry.UpdateJewelryUseCase;
import com.cplerings.core.application.jewelry.input.UpdateJewelryInput;
import com.cplerings.core.application.jewelry.output.UpdateJewelryOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UpdateJewelryController extends AbstractController<UpdateJewelryInput, UpdateJewelryOutput, AJewelry, UpdateJewelryRequest, UpdateJewelryResponse> {

    private final APIUpdateJewelryMapper apiUpdateJewelryMapper;
    private final UpdateJewelryUseCase updateJewelryUseCase;

    @Override
    protected UseCase<UpdateJewelryInput, UpdateJewelryOutput> getUseCase() {
        return updateJewelryUseCase;
    }

    @Override
    protected APIMapper<UpdateJewelryInput, UpdateJewelryOutput, AJewelry, UpdateJewelryRequest, UpdateJewelryResponse> getMapper() {
        return apiUpdateJewelryMapper;
    }

    @PutMapping(APIConstant.JEWELRY_PATH)
    @JewelryTag
    @Operation(summary = "update jewelry")
    @ApiResponse(
            description = "jewelry information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateJewelryResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> update(@PathVariable("jewelryId") Long jewelryId,
                                         @RequestBody UpdateJewelryRequestData data) {
        UpdateJewelryRequest request = UpdateJewelryRequest.builder()
                .designId(data.designId())
                .metalSpecId(data.metalSpecId())
                .jewelryId(jewelryId)
                .build();
        return handleRequest(request);
    }
}
