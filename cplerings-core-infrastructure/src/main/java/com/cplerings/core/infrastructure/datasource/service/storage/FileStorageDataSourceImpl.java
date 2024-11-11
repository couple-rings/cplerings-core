package com.cplerings.core.infrastructure.datasource.service.storage;

import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.service.storage.FileStorageDataSource;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class FileStorageDataSourceImpl extends AbstractDataSource implements FileStorageDataSource {

    private final ImageRepository imageRepository;
    private final DocumentRepository documentRepository;

    @Override
    public Image save(Image image) {
        updateAuditor(image);
        return imageRepository.save(image);
    }

    @Override
    public Document save(Document document) {
        updateAuditor(document);
        return documentRepository.save(document);
    }
}
