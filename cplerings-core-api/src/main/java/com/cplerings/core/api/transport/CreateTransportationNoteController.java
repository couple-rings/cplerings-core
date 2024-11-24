package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.mapper.APICreateTransportationNoteMapper;
import com.cplerings.core.api.transport.request.CreateTransportationNoteRequest;
import com.cplerings.core.api.transport.response.CreateTransportationNoteResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.CreateTransportationNoteUseCase;
import com.cplerings.core.application.transport.input.CreateTransportationNoteInput;
import com.cplerings.core.application.transport.output.CreateTransportationNoteOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CreateTransportationNoteController extends AbstractController<CreateTransportationNoteInput, CreateTransportationNoteOutput, ATransportationNote, CreateTransportationNoteRequest, CreateTransportationNoteResponse> {

    private final CreateTransportationNoteUseCase createTransportationNoteUseCase;
    private final APICreateTransportationNoteMapper apiCreateTransportationNoteMapper;

    @Override
    protected UseCase<CreateTransportationNoteInput, CreateTransportationNoteOutput> getUseCase() {
        return createTransportationNoteUseCase;
    }

    @Override
    protected APIMapper<CreateTransportationNoteInput, CreateTransportationNoteOutput, ATransportationNote, CreateTransportationNoteRequest, CreateTransportationNoteResponse> getMapper() {
        return apiCreateTransportationNoteMapper;
    }

    @PostMapping(APIConstant.CREATE_TRANSPORTATION_NOTE_PATH)
    @OrderTag
    @Operation(summary = "Create Transportation note")
    @ApiResponse(
            description = "Transportation note information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateTransportationNoteResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateTransportationNoteRequest request) {
        return handleRequest(request);
    }
}
