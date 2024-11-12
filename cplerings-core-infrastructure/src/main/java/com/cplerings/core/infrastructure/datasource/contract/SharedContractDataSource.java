package com.cplerings.core.infrastructure.datasource.contract;

import java.util.Optional;

import com.cplerings.core.application.contract.datasource.DetermineContractDataSource;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.contract.QContract;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.file.QImage;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.ContractRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedContractDataSource extends AbstractDataSource implements DetermineContractDataSource {

    private static final QContract Q_CONTRACT = QContract.contract;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QImage Q_IMAGE = QImage.image;

    private final ContractRepository contractRepository;

    @Override
    public Optional<Contract> getContractById(Long contractId) {
        return Optional.ofNullable(createQuery().select(Q_CONTRACT)
                .from(Q_CONTRACT)
                .where(Q_CONTRACT.id.eq(contractId))
                .fetchOne());
    }

    @Override
    public Contract save(Contract contract) {
        updateAuditor(contract);
        return contractRepository.save(contract);
    }

    @Override
    public Optional<Document> getDocumentById(Long documentId) {
        return Optional.ofNullable(createQuery().select(Q_DOCUMENT)
                .from(Q_DOCUMENT)
                .where(Q_DOCUMENT.id.eq(documentId))
                .fetchOne());
    }

    @Override
    public Optional<Image> getImageById(Long imageId) {
        return Optional.ofNullable(createQuery().select(Q_IMAGE)
                .from(Q_IMAGE)
                .where(Q_IMAGE.id.eq(imageId))
                .fetchOne());
    }
}
