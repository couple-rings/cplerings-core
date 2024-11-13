package com.cplerings.core.api.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.TransportersData;
import com.cplerings.core.api.account.mapper.APIViewTransportersMapper;
import com.cplerings.core.api.account.request.ViewTransportersRequest;
import com.cplerings.core.api.account.response.ViewTransportersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ViewTransportersUseCase;
import com.cplerings.core.application.account.input.ViewTransportersInput;
import com.cplerings.core.application.account.output.ViewTransportersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTransportersController extends AbstractController<ViewTransportersInput, ViewTransportersOutput, TransportersData, ViewTransportersRequest, ViewTransportersResponse> {

    private final APIViewTransportersMapper apiViewTransportersMapper;
    private final ViewTransportersUseCase viewTransportersUseCase;

    @Override
    protected UseCase<ViewTransportersInput, ViewTransportersOutput> getUseCase() {
        return viewTransportersUseCase;
    }

    @Override
    protected APIMapper<ViewTransportersInput, ViewTransportersOutput, TransportersData, ViewTransportersRequest, ViewTransportersResponse> getMapper() {
        return apiViewTransportersMapper;
    }

    @GetMapping(APIConstant.TRANSPORTERS_PATH)
    @AccountTag
    @Operation(summary = "View transporters of a branch")
    @ApiResponse(
            description = "The transporters",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTransportersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewTransportersRequest request) {
        return handleRequest(request);
    }
}
