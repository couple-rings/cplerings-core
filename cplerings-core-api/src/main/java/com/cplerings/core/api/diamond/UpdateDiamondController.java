package com.cplerings.core.api.diamond;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.diamond.mapper.APIUpdateDiamondMapper;
import com.cplerings.core.api.diamond.request.UpdateDiamondRequest;
import com.cplerings.core.api.diamond.request.data.UpdateDiamondRequestData;
import com.cplerings.core.api.diamond.response.UpdateDiamondResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DiamondTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.diamond.UpdateDiamondUseCase;
import com.cplerings.core.application.diamond.input.UpdateDiamondInput;
import com.cplerings.core.application.diamond.output.UpdateDiamondOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UpdateDiamondController extends AbstractController<UpdateDiamondInput, UpdateDiamondOutput, ADiamond, UpdateDiamondRequest, UpdateDiamondResponse> {

    private final UpdateDiamondUseCase useCase;
    private final APIUpdateDiamondMapper apiUpdateDiamondMapper;

    @Override
    protected UseCase<UpdateDiamondInput, UpdateDiamondOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<UpdateDiamondInput, UpdateDiamondOutput, ADiamond, UpdateDiamondRequest, UpdateDiamondResponse> getMapper() {
        return apiUpdateDiamondMapper;
    }

    @PutMapping(APIConstant.SINGLE_DIAMOND_PATH)
    @DiamondTag
    @Operation(summary = "Update diamond")
    @ApiResponse(
            description = "Diamond updated infor",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateDiamondResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@PathVariable("diamondId") Long diamondId, @RequestBody UpdateDiamondRequestData updateDiamondRequestData) {
        UpdateDiamondRequest request = UpdateDiamondRequest.builder()
                .diamondId(diamondId)
                .diamondSpecificationId(updateDiamondRequestData.diamondSpecificationId())
                .giaDocumentId(updateDiamondRequestData.giaDocumentId())
                .giaReportNumber(updateDiamondRequestData.giaReportNumber())
                .build();
        return handleRequest(request);
    }
}
