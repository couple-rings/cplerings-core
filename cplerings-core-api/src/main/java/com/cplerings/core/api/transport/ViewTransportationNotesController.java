package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.MetalSpecificationTag;
import com.cplerings.core.api.transport.data.TransportationNotesData;
import com.cplerings.core.api.transport.mapper.APIViewTransportationNotesMapper;
import com.cplerings.core.api.transport.request.ViewTransportationNotesRequest;
import com.cplerings.core.api.transport.response.ViewTransportationNotesResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.ViewTransportationNotesUseCase;
import com.cplerings.core.application.transport.input.ViewTransportationNotesInput;
import com.cplerings.core.application.transport.output.ViewTransportationNotesOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTransportationNotesController extends AbstractController<ViewTransportationNotesInput, ViewTransportationNotesOutput, TransportationNotesData, ViewTransportationNotesRequest, ViewTransportationNotesResponse> {

    private final APIViewTransportationNotesMapper viewTransportationNotesMapper;
    private final ViewTransportationNotesUseCase viewTransportationNotesUseCase;

    @Override
    protected UseCase<ViewTransportationNotesInput, ViewTransportationNotesOutput> getUseCase() {
        return viewTransportationNotesUseCase;
    }

    @Override
    protected APIMapper<ViewTransportationNotesInput, ViewTransportationNotesOutput, TransportationNotesData, ViewTransportationNotesRequest, ViewTransportationNotesResponse> getMapper() {
        return viewTransportationNotesMapper;
    }

    @GetMapping(APIConstant.TRANSPORTATION_NOTE_PATH)
    @MetalSpecificationTag
    @Operation(summary = "View transportation notes")
    @ApiResponse(
            description = "View transportation notes information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTransportationNotesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewTransportationNotesRequest request) {
        return handleRequest(request);
    }
}
