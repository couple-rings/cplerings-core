package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewCustomOrdersWithDateData;
import com.cplerings.core.api.dashboard.mapper.APIViewCustomOrdersWithDateMapper;
import com.cplerings.core.api.dashboard.request.ViewCustomOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewCustomOrdersWithDateResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewCustomOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.input.ViewCustomOrdersWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewCustomOrdersWithDateOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCustomOrdersWithDateController extends AbstractController<ViewCustomOrdersWithDateInput, ViewCustomOrdersWithDateOutput, ViewCustomOrdersWithDateData, ViewCustomOrdersWithDateRequest, ViewCustomOrdersWithDateResponse> {

    private final APIViewCustomOrdersWithDateMapper apiViewCustomOrdersWithDateMapper;
    private final ViewCustomOrdersWithDateUseCase useCase;

    @Override
    protected UseCase<ViewCustomOrdersWithDateInput, ViewCustomOrdersWithDateOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewCustomOrdersWithDateInput, ViewCustomOrdersWithDateOutput, ViewCustomOrdersWithDateData, ViewCustomOrdersWithDateRequest, ViewCustomOrdersWithDateResponse> getMapper() {
        return apiViewCustomOrdersWithDateMapper;
    }

    @GetMapping(APIConstant.CUSTOM_ORDERS_STATISTIC_PAGINATION_PATH)
    @DashboardTag
    @Operation(summary = "View custom orders")
    @ApiResponse(
            description = "The custom orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomOrdersWithDateResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCustomOrdersWithDateRequest request) {
        return handleRequest(request);
    }
}
