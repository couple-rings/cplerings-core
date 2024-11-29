package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.jewelry.JewelryCategory;

public interface CreateDesignDataSource {

    Optional<JewelryCategory> getJewelryCategoryById(Long id);

    Optional<Document> getBlueprintById(Long id);

    Optional<DesignCollection> getDesignCollectionById(Long id);

    Design save(Design design);
}
