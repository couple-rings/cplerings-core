package com.cplerings.core.application.design.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.CustomDesignDiamondSpecification;
import com.cplerings.core.domain.design.CustomDesignMetalSpecification;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.spouse.Spouse;

public interface CreateCustomDesignDataSource {

    CustomDesign save(CustomDesign customDesign);

    Optional<Account> getCustomerById(Long customerId);

    Optional<Spouse> getSpouseById(Long spouseId);

    Optional<DesignVersion> getDesignVersionById(Long designVersionId);

    Optional<CustomDesign> getCustomDesignBySpouseId(Long spouseId);

    Optional<CustomDesign> getCustomDesignByDesignVersionId(Long designVersionId);

    Optional<Document> getDocumentById(Long id);

    List<MetalSpecification> getMetalSpecById(List<Long> ids);

    List<DiamondSpecification> getDiamondSpecById(List<Long> ids);

    List<CustomDesignMetalSpecification> saveMetalSpec(List<CustomDesignMetalSpecification> customDesignMetalSpecifications);

    List<CustomDesignDiamondSpecification> saveDiamondSpec(List<CustomDesignDiamondSpecification> customDesignDiamondSpecifications);
}
