package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.mapper.APICreateTransportationAddressMapper;
import com.cplerings.core.api.transport.request.CreateTransportationAddressRequest;
import com.cplerings.core.api.transport.request.UpdateTransportationOrderStatusRequest;
import com.cplerings.core.api.transport.request.data.TransportationAddressData;
import com.cplerings.core.api.transport.request.data.UpdateTransportationOrderStatusRequestData;
import com.cplerings.core.api.transport.response.CreateTransportationAddressResponse;
import com.cplerings.core.api.transport.response.UpdateTransportationOrderStatusResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.CreateTransportationAddressUseCase;
import com.cplerings.core.application.transport.input.CreateTransportationAddressInput;
import com.cplerings.core.application.transport.output.CreateTransportationAddressOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CreateTransportationAddressController extends AbstractController<CreateTransportationAddressInput, CreateTransportationAddressOutput, TransportationAddressData, CreateTransportationAddressRequest, CreateTransportationAddressResponse> {

    private final CreateTransportationAddressUseCase createTransportationAddressUseCase;
    private final APICreateTransportationAddressMapper apiCreateTransportationAddressMapper;

    @Override
    protected UseCase<CreateTransportationAddressInput, CreateTransportationAddressOutput> getUseCase() {
        return createTransportationAddressUseCase;
    }

    @Override
    protected APIMapper<CreateTransportationAddressInput, CreateTransportationAddressOutput, TransportationAddressData, CreateTransportationAddressRequest, CreateTransportationAddressResponse> getMapper() {
        return apiCreateTransportationAddressMapper;
    }

    @PostMapping(APIConstant.ADDRESS_PATH)
    @OrderTag
    @Operation(summary = "Create Transportation address")
    @ApiResponse(
            description = "Transportation address information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateTransportationAddressResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateTransportationAddressRequest request) {
        return handleRequest(request);
    }
}
