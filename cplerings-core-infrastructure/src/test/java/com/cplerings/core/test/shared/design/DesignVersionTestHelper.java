package com.cplerings.core.test.shared.design;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class DesignVersionTestHelper {

    private final TestDataSource testDataSource;
    private final DocumentRepository documentRepository;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final DesignRepository designRepository;

    public DesignVersion createDesignVersion() {
        DesignVersion designVersion = DesignVersion.builder()
                .designFile(documentRepository.getReferenceById(1L))
                .customer(accountRepository.getReferenceById(1L))
                .image(imageRepository.getReferenceById(1L))
                .design(designRepository.getReferenceById(1L))
                .versionNumber(3)
                .isAccepted(true)
                .isOld(false)
                .build();
        return testDataSource.save(designVersion);
    }

    public DesignVersion createDesignVersion2() {
        DesignVersion designVersion = DesignVersion.builder()
                .designFile(documentRepository.getReferenceById(11L))
                .customer(accountRepository.getReferenceById(1L))
                .image(imageRepository.getReferenceById(11L))
                .design(designRepository.getReferenceById(11L))
                .versionNumber(3)
                .isAccepted(false)
                .isOld(false)
                .build();
        return testDataSource.save(designVersion);
    }
}
