package com.cplerings.core.application.contract.datasource;

import java.util.Optional;

import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;

public interface DetermineContractDataSource {

    Optional<Contract> getContractById(Long contractId);
    Contract save(Contract contract);
    Optional<Document> getDocumentById(Long documentId);
    Optional<Image> getImageById(Long imageId);
}