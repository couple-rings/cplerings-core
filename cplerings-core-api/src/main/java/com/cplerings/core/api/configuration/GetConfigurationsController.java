package com.cplerings.core.api.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.configuration.data.GetConfigurationsData;
import com.cplerings.core.api.configuration.mapper.APIGetConfigurationsMapper;
import com.cplerings.core.api.configuration.request.GetConfigurationsRequest;
import com.cplerings.core.api.configuration.response.GetConfigurationsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ConfigurationTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.configuration.GetConfigurationsUseCase;
import com.cplerings.core.application.configuration.input.GetConfigurationsInput;
import com.cplerings.core.application.configuration.output.GetConfigurationsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetConfigurationsController extends AbstractController<GetConfigurationsInput, GetConfigurationsOutput, GetConfigurationsData, GetConfigurationsRequest, GetConfigurationsResponse> {

    private final APIGetConfigurationsMapper apiGetConfigurationsMapper;
    private final GetConfigurationsUseCase getConfigurationsUseCase;

    @Override
    protected UseCase<GetConfigurationsInput, GetConfigurationsOutput> getUseCase() {
        return getConfigurationsUseCase;
    }

    @Override
    protected APIMapper<GetConfigurationsInput, GetConfigurationsOutput, GetConfigurationsData, GetConfigurationsRequest, GetConfigurationsResponse> getMapper() {
        return apiGetConfigurationsMapper;
    }

    @GetMapping(APIConstant.CONFIGURATIONS_PATH)
    @ConfigurationTag
    @Operation(summary = "Get Configurations")
    @ApiResponse(
            description = "Configurations data",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetConfigurationsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(GetConfigurationsRequest request) {
        return handleRequest(request);
    }
}
