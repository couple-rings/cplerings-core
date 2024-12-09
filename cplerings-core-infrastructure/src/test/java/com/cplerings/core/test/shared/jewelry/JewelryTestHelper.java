package com.cplerings.core.test.shared.jewelry;

import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.MetalSpecificationRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class JewelryTestHelper {

    private final DesignRepository designRepository;
    private final MetalSpecificationRepository metalSpecificationRepository;
    private final BranchRepository branchRepository;
    private final TestDataSource testDataSource;

    public Jewelry createJewelry() {
        Jewelry jewelry = Jewelry.builder()
                .status(JewelryStatus.AVAILABLE)
                .design(designRepository.getReferenceById(1L))
                .metalSpecification(metalSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .build();
        return testDataSource.save(jewelry);
    }
}
