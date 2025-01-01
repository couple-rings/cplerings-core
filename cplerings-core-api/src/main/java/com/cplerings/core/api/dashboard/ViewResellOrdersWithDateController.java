package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewResellOrdersWithDateData;
import com.cplerings.core.api.dashboard.mapper.APIViewResellOrdersWithDateMapper;
import com.cplerings.core.api.dashboard.request.ViewResellOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewResellOrdersWithDateResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewResellOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.input.ViewResellOrdersWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewResellOrdersWithDateOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewResellOrdersWithDateController extends AbstractController<ViewResellOrdersWithDateInput, ViewResellOrdersWithDateOutput, ViewResellOrdersWithDateData, ViewResellOrdersWithDateRequest, ViewResellOrdersWithDateResponse> {

    private final ViewResellOrdersWithDateUseCase useCase;
    private final APIViewResellOrdersWithDateMapper apiViewResellOrdersWithDateMapper;

    @Override
    protected UseCase<ViewResellOrdersWithDateInput, ViewResellOrdersWithDateOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewResellOrdersWithDateInput, ViewResellOrdersWithDateOutput, ViewResellOrdersWithDateData, ViewResellOrdersWithDateRequest, ViewResellOrdersWithDateResponse> getMapper() {
        return apiViewResellOrdersWithDateMapper;
    }

    @GetMapping(APIConstant.RESELL_ORDERS_STATISTIC_PAGINATION_PATH)
    @DashboardTag
    @Operation(summary = "View resell orders")
    @ApiResponse(
            description = "The resell orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewResellOrdersWithDateResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewResellOrdersWithDateRequest request) {
        return handleRequest(request);
    }
}
