package com.cplerings.core.test.shared.diamond;

import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.FileTestHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.util.UUID;

@TestComponent
public class DiamondTestHelper {

    @Autowired
    private DiamondSpecificationRepository diamondSpecificationRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private FileTestHelper fileTestHelper;

    public Diamond createDiamond() {
        Diamond diamond = Diamond.builder()
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .giaDocument(documentRepository.getReferenceById(1L))
                .giaReportNumber("test")
                .build();
        return testDataSource.save(diamond);
    }

    public Diamond createNewDiamond() {
        Diamond diamond = Diamond.builder()
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .giaDocument(fileTestHelper.createDocument())
                .giaReportNumber(UUID.randomUUID().toString())
                .build();
        return testDataSource.save(diamond);
    }
}
