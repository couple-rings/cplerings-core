package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.mapper.APIGetCustomOrderNoMapper;
import com.cplerings.core.api.order.request.GetCustomOrderByOrderNoRequest;
import com.cplerings.core.api.order.response.GetCustomOrderByOrderNoResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.GetCustomOrderByOrderNoUseCase;
import com.cplerings.core.application.order.input.GetCustomOrderByOrderNoInput;
import com.cplerings.core.application.order.output.GetCustomOrderByOrderNoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetCustomOrderByOrderNoController extends AbstractController<GetCustomOrderByOrderNoInput, GetCustomOrderByOrderNoOutput, CustomOrderData, GetCustomOrderByOrderNoRequest, GetCustomOrderByOrderNoResponse> {

    private final APIGetCustomOrderNoMapper apiGetCustomOrderNoMapper;
    private final GetCustomOrderByOrderNoUseCase getCustomOrderByOrderNoUseCase;

    @Override
    protected UseCase<GetCustomOrderByOrderNoInput, GetCustomOrderByOrderNoOutput> getUseCase() {
        return getCustomOrderByOrderNoUseCase;
    }

    @Override
    protected APIMapper<GetCustomOrderByOrderNoInput, GetCustomOrderByOrderNoOutput, CustomOrderData, GetCustomOrderByOrderNoRequest, GetCustomOrderByOrderNoResponse> getMapper() {
        return apiGetCustomOrderNoMapper;
    }

    @GetMapping(APIConstant.VIEW_A_CUSTOM_ORDER_BY_ORDER_NO_PATH)
    @OrderTag
    @Operation(summary = "Get custom order by orderNo")
    @ApiResponse(
            description = "Custom order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetCustomOrderByOrderNoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("orderNo") String orderNo) {
        GetCustomOrderByOrderNoRequest request = GetCustomOrderByOrderNoRequest.builder().orderNo(orderNo).build();
        return handleRequest(request);
    }
}
