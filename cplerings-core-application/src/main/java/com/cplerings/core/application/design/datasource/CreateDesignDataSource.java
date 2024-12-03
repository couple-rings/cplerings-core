package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.design.DesignMetalSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.JewelryCategory;
import com.cplerings.core.domain.metal.MetalSpecification;

public interface CreateDesignDataSource {

    Optional<JewelryCategory> getJewelryCategoryById(Long id);

    Optional<Document> getBlueprintById(Long id);

    Optional<DesignCollection> getDesignCollectionById(Long id);

    Design save(Design design);

    Optional<MetalSpecification> getMetalSpecificationById(Long id);

    Optional<Image> getImageById(Long id);

    DesignMetalSpecification save(DesignMetalSpecification metalSpecification);
}
