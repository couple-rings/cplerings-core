package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalInForAllData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalInForAllMapper;
import com.cplerings.core.api.dashboard.response.ViewTotalInForAllResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalInForAllUseCase;
import com.cplerings.core.application.dashboard.output.ViewTotalInForAllOutput;
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
public class ViewTotalInForAllController extends AbstractController<NoInput, ViewTotalInForAllOutput, ViewTotalInForAllData, NoRequest, ViewTotalInForAllResponse> {

    private final ViewTotalInForAllUseCase useCase;
    private final APIViewTotalInForAllMapper mapper;

    @Override
    protected UseCase<NoInput, ViewTotalInForAllOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<NoInput, ViewTotalInForAllOutput, ViewTotalInForAllData, NoRequest, ViewTotalInForAllResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_INCOME_PATH)
    @DashboardTag
    @Operation(summary = "View total income")
    @ApiResponse(
            description = "The total income",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalInForAllResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(NoRequest request) {
        return handleRequest(request);
    }
}
