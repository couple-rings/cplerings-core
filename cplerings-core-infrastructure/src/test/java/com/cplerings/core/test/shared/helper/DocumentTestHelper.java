package com.cplerings.core.test.shared.helper;

import com.cplerings.core.domain.file.Document;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.util.UUID;

@TestComponent
@RequiredArgsConstructor
public class DocumentTestHelper {

    private final TestDataSource testDataSource;

    public Document createDocument() {
        final Document document = Document.builder()
                .url(UUID.randomUUID().toString())
                .build();
        return testDataSource.save(document);
    }
}
