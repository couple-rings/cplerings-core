package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;

public interface CreateDesignVersionDataSource {

    DesignVersion save(DesignVersion designVersion);
    void saveDocument(Document document);
    void saveImage(Image image);
    Optional<Design> getDesignByID(long designID);
}
