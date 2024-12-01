package com.cplerings.core.test.shared.diamond;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

@TestComponent
public class DiamondTestHelper {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private DiamondSpecificationRepository diamondSpecificationRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TestDataSource testDataSource;

    public Diamond createDiamond() {
        Diamond diamond = Diamond.builder()
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .giaDocument(documentRepository.getReferenceById(1L))
                .giaReportNumber("test")
                .build();
        return testDataSource.save(diamond);
    }
}
