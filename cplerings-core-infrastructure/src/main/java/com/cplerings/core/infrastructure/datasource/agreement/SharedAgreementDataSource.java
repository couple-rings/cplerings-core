package com.cplerings.core.infrastructure.datasource.agreement;

import com.cplerings.core.application.agreement.datasource.SignAgreementDataSource;
import com.cplerings.core.application.agreement.datasource.ViewAgreementsDataSource;
import com.cplerings.core.application.agreement.datasource.result.Agreements;
import com.cplerings.core.application.agreement.input.ViewAgreementsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.domain.spouse.QAgreement;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AgreementRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@DataSource
public class SharedAgreementDataSource extends AbstractDataSource
        implements ViewAgreementsDataSource, SignAgreementDataSource {

    private final static QAgreement Q_AGREEMENT = QAgreement.agreement;

    private final ImageRepository imageRepository;
    private final AgreementRepository agreementRepository;

    public SharedAgreementDataSource(ImageRepository imageRepository, AgreementRepository agreementRepository) {
        super();
        this.imageRepository = imageRepository;
        this.agreementRepository = agreementRepository;
    }

    @Override
    public Agreements getAgreements(ViewAgreementsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Agreement> query = createQuery()
                .select(Q_AGREEMENT)
                .from(Q_AGREEMENT);
        long count = query.distinct().fetchCount();
        List<Agreement> agreements = query.limit(input.getPageSize()).offset(offset).fetch();
        return Agreements.builder()
                .agreements(agreements)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Collection<Image> findSignaturesByIds(Collection<Long> signatureIds) {
        return imageRepository.findAllByIdIn(signatureIds);
    }

    @Override
    public Optional<Agreement> findAgreementById(Long agreementId) {
        return agreementRepository.findById(agreementId);
    }

    @Override
    public Agreement save(Agreement agreement) {
        updateAuditor(agreement);
        return agreementRepository.save(agreement);
    }
}
