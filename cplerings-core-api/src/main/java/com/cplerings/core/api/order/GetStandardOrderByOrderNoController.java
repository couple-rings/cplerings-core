package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.mapper.APIGetStandardOrderMapper;
import com.cplerings.core.api.order.request.GetStandardOrderByOrderNoRequest;
import com.cplerings.core.api.order.response.GetStandardOrderByOrderNoResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.GetStandardOrderByOrderNoUseCase;
import com.cplerings.core.application.order.input.GetStandardOrderByOrderNoInput;
import com.cplerings.core.application.order.output.GetStandardOrderByOrderNoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetStandardOrderByOrderNoController extends AbstractController<GetStandardOrderByOrderNoInput, GetStandardOrderByOrderNoOutput, StandardOrderData, GetStandardOrderByOrderNoRequest, GetStandardOrderByOrderNoResponse> {

    private final APIGetStandardOrderMapper apiGetStandardOrderMapper;
    private final GetStandardOrderByOrderNoUseCase getStandardOrderByOrderNoUseCase;

    @Override
    protected UseCase<GetStandardOrderByOrderNoInput, GetStandardOrderByOrderNoOutput> getUseCase() {
        return getStandardOrderByOrderNoUseCase;
    }

    @Override
    protected APIMapper<GetStandardOrderByOrderNoInput, GetStandardOrderByOrderNoOutput, StandardOrderData, GetStandardOrderByOrderNoRequest, GetStandardOrderByOrderNoResponse> getMapper() {
        return apiGetStandardOrderMapper;
    }

    @GetMapping(APIConstant.VIEW_A_STANDARD_ORDER_BY_ORDER_NO_PATH)
    @OrderTag
    @Operation(summary = "Get standard order by orderNo")
    @ApiResponse(
            description = "Standard order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetStandardOrderByOrderNoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("orderNo") String orderNo) {
        GetStandardOrderByOrderNoRequest request = GetStandardOrderByOrderNoRequest.builder().orderNo(orderNo).build();
        return handleRequest(request);
    }
}
