package com.cplerings.core.application.diamond.datasource;

import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.file.Document;

import java.util.Optional;

public interface CreateDiamondDataSource {


    Optional<Document> findGIADocumentById(Long giaDocumentId);

    Optional<DiamondSpecification> findDiamondSpecificationById(Long diamondSpecificationId);

    Optional<Branch> findBranchById(Long branchId);

    Diamond save(Diamond diamond);
}
