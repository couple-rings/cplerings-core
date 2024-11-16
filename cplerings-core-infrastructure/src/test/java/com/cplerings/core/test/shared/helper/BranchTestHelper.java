package com.cplerings.core.test.shared.helper;

import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class BranchTestHelper {

    private final TestDataSource testDataSource;
    private final ImageRepository imageRepository;

    public Branch createBranch() {
        Branch branch = Branch.builder()
                .storeName("Branch 1")
                .storeName("Store 1")
                .phone("1234567890")
                .address("123 Hello")
                .coverImage(imageRepository.getReferenceById(1L))
                .build();
        return testDataSource.save(branch);
    }
}
