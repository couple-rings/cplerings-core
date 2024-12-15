package com.cplerings.core.api.jewelry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.jewelry.mapper.APIGetJewelryByProductNoMapper;
import com.cplerings.core.api.jewelry.request.GetJewelryByProductNoRequest;
import com.cplerings.core.api.jewelry.response.GetJewelryByProductNoResponse;
import com.cplerings.core.api.order.request.GetStandardOrderByOrderNoRequest;
import com.cplerings.core.api.order.response.GetStandardOrderByOrderNoResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.jewelry.GetJewelryByProductNoUseCase;
import com.cplerings.core.application.jewelry.input.GetJewelryByProductNoInput;
import com.cplerings.core.application.jewelry.output.GetJewelryByProductNoOutput;
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
public class GetJewelryByProductNoController extends AbstractController<GetJewelryByProductNoInput, GetJewelryByProductNoOutput, AJewelry, GetJewelryByProductNoRequest, GetJewelryByProductNoResponse> {

    private final APIGetJewelryByProductNoMapper mapper;
    private final GetJewelryByProductNoUseCase getJewelryByProductNoUseCase;

    @Override
    protected UseCase<GetJewelryByProductNoInput, GetJewelryByProductNoOutput> getUseCase() {
        return getJewelryByProductNoUseCase;
    }

    @Override
    protected APIMapper<GetJewelryByProductNoInput, GetJewelryByProductNoOutput, AJewelry, GetJewelryByProductNoRequest, GetJewelryByProductNoResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.JEWELRY_PRODUCT_NO_PATH)
    @OrderTag
    @Operation(summary = "Get jewelry by productNo")
    @ApiResponse(
            description = "Jewelry information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetJewelryByProductNoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("productNo") String productNo) {
        GetJewelryByProductNoRequest request = GetJewelryByProductNoRequest.builder().productNo(productNo).build();
        return handleRequest(request);
    }
}
