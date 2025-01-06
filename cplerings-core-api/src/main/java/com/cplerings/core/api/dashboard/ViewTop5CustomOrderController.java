package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTop5CustomOrderData;
import com.cplerings.core.api.dashboard.mapper.APIViewTop5CustomOrderMapper;
import com.cplerings.core.api.dashboard.response.ViewTop5CustomOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTop5CustomOrderUseCase;
import com.cplerings.core.application.dashboard.output.ViewTop5CustomOrderOutputData;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTop5CustomOrderController extends AbstractController<NoInput, ViewTop5CustomOrderOutputData, ViewTop5CustomOrderData, NoRequest, ViewTop5CustomOrderResponse> {

    private final APIViewTop5CustomOrderMapper mapper;
    private final ViewTop5CustomOrderUseCase useCase;

    @Override
    protected UseCase<NoInput, ViewTop5CustomOrderOutputData> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<NoInput, ViewTop5CustomOrderOutputData, ViewTop5CustomOrderData, NoRequest, ViewTop5CustomOrderResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOP_5_CUSTOM_ORDER)
    @DashboardTag
    @Operation(summary = "View total expenditure")
    @ApiResponse(
            description = "The total expenditure",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTop5CustomOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(NoRequest request) {
        return handleRequest(request);
    }
}
