package com.cplerings.core.api.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.contract.data.Contract;
import com.cplerings.core.api.contract.mapper.APIDetermineContractMapper;
import com.cplerings.core.api.contract.request.DetermineContractRequest;
import com.cplerings.core.api.contract.request.data.DetermineContractRequestData;
import com.cplerings.core.api.contract.response.DetermineContractResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.contract.DetermineContractUseCase;
import com.cplerings.core.application.contract.input.DetermineContractInput;
import com.cplerings.core.application.contract.output.DetermineContractOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetermineContractController extends AbstractController<DetermineContractInput, DetermineContractOutput, Contract, DetermineContractRequest, DetermineContractResponse> {

    private final DetermineContractUseCase determineContractUseCase;
    private final APIDetermineContractMapper apiDetermineContractMapper;

    @Override
    protected UseCase<DetermineContractInput, DetermineContractOutput> getUseCase() {
        return determineContractUseCase;
    }

    @Override
    protected APIMapper<DetermineContractInput, DetermineContractOutput, Contract, DetermineContractRequest, DetermineContractResponse> getMapper() {
        return apiDetermineContractMapper;
    }

    @PutMapping(APIConstant.SIGNING_CONTRACT_PATH)
    @CraftingRequestTag
    @Operation(summary = "Signing contract")
    @ApiResponse(
            description = "Contract information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = DetermineContractResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> sign(@PathVariable("contractId") Long contractId, @RequestBody DetermineContractRequestData determineContractRequestData) {
        DetermineContractRequest request = DetermineContractRequest.builder()
                .documentId(determineContractRequestData.getDocumentId())
                .contractId(contractId)
                .signature(determineContractRequestData.getSignature())
                .signedDate(determineContractRequestData.getSignedDate())
                .build();
        return handleRequest(request);
    }
}
