package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.data.TransportationAddressesData;
import com.cplerings.core.api.transport.mapper.APIViewTransportationAddressesMapper;
import com.cplerings.core.api.transport.request.ViewTransportationAddressesRequest;
import com.cplerings.core.api.transport.response.ViewTransportationAddressesResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.ViewTransportationAddressesUseCase;
import com.cplerings.core.application.transport.input.ViewTransportationAddressesInput;
import com.cplerings.core.application.transport.output.ViewTransportationAddressesOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTransportationAddressesController extends AbstractController<ViewTransportationAddressesInput, ViewTransportationAddressesOutput, TransportationAddressesData, ViewTransportationAddressesRequest, ViewTransportationAddressesResponse> {

    private final APIViewTransportationAddressesMapper apiViewTransportationAddressesMapper;
    private final ViewTransportationAddressesUseCase viewTransportationAddressesUseCase;

    @Override
    protected UseCase<ViewTransportationAddressesInput, ViewTransportationAddressesOutput> getUseCase() {
        return viewTransportationAddressesUseCase;
    }

    @Override
    protected APIMapper<ViewTransportationAddressesInput, ViewTransportationAddressesOutput, TransportationAddressesData, ViewTransportationAddressesRequest, ViewTransportationAddressesResponse> getMapper() {
        return apiViewTransportationAddressesMapper;
    }

    @GetMapping(APIConstant.ADDRESS_PATH)
    @OrderTag
    @Operation(summary = "view transportation addresses")
    @ApiResponse(
            description = "Transportation addresses information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTransportationAddressesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> update(ViewTransportationAddressesRequest request) {
        return handleRequest(request);
    }
}
