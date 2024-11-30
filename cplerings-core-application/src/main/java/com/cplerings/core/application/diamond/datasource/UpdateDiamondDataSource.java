package com.cplerings.core.application.diamond.datasource;

import java.util.Optional;

import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.file.Document;

public interface UpdateDiamondDataSource {

    Optional<Diamond> getDiamondById(Long id);

    Optional<DiamondSpecification> getDiamondSpecById(Long id);

    Optional<Diamond> getDiamondByGiaReportNumberAndNotEqualTheDiamondId(String giaReportNumber, Long diamondId);

    Optional<Document> getDocumentById(Long id);

    Diamond save(Diamond diamond);
}
