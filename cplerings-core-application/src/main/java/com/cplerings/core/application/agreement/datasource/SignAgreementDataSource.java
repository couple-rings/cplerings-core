package com.cplerings.core.application.agreement.datasource;

import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.spouse.Agreement;

import java.util.Collection;
import java.util.Optional;

public interface SignAgreementDataSource {

    Collection<Image> findSignaturesByIds(Collection<Long> signatureIds);

    Optional<Agreement> findAgreementById(Long agreementId);

    Agreement save(Agreement agreement);
}
