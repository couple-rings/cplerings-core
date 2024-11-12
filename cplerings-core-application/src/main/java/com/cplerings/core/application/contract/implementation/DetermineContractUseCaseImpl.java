package com.cplerings.core.application.contract.implementation;

import com.cplerings.core.application.contract.DetermineContractUseCase;
import com.cplerings.core.application.contract.datasource.DetermineContractDataSource;
import com.cplerings.core.application.contract.error.DetermineContractErrorCode;
import com.cplerings.core.application.contract.input.DetermineContractInput;
import com.cplerings.core.application.contract.mapper.ADetermineContractMapper;
import com.cplerings.core.application.contract.output.DetermineContractOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.file.Document;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class DetermineContractUseCaseImpl extends AbstractUseCase<DetermineContractInput, DetermineContractOutput> implements DetermineContractUseCase {

    private final DetermineContractDataSource determineContractDataSource;
    private final ADetermineContractMapper aDetermineContractMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, DetermineContractInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.documentId() != null, DetermineContractErrorCode.DOCUMENT_ID_REQUIRED);
        validator.validateAndStopExecution(input.signature() != null, DetermineContractErrorCode.SIGNATURE_REQUIRED);
        validator.validateAndStopExecution(input.signedDate() != null, DetermineContractErrorCode.SIGNED_DATE_REQUIRED);
        validator.validateAndStopExecution(input.documentId() > 0, DetermineContractErrorCode.DOCUMENT_ID_WRONG_POSITIVE_INTEGER);
        validator.validateAndStopExecution(input.contractId() > 0, DetermineContractErrorCode.CONTRACT_ID_WRONG_POSITIVE_INTEGER);
    }

    @Override
    protected DetermineContractOutput internalExecute(UseCaseValidator validator, DetermineContractInput input) {
        Contract contract = determineContractDataSource.getContractById(input.contractId())
                .orElse(null);
        validator.validateAndStopExecution(contract != null, DetermineContractErrorCode.INVALID_CONTRACT_ID);
        Document document = determineContractDataSource.getDocumentById(input.documentId())
                .orElse(null);
        validator.validateAndStopExecution(document != null, DetermineContractErrorCode.INVALID_DOCUMENT_ID);
        contract.setDocument(document);
        contract.setSignature(input.signature());
        contract.setSignedDate(input.signedDate());
        return aDetermineContractMapper.toOutput(determineContractDataSource.save(contract));
    }
}
