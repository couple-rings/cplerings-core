package com.cplerings.core.infrastructure.service.storage;

import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;

public interface FileStorageDataSource {

    Image save(Image image);

    Document save(Document document);
}
