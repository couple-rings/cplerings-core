package com.cplerings.core.infrastructure.datasource.customrequest;

import java.util.Optional;

import com.cplerings.core.application.customrequest.datrasource.ViewCustomRequestDataSource;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.QCustomRequest;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedCustomRequestDataSource extends AbstractDataSource implements ViewCustomRequestDataSource {

    private final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;

    @Override
    public Optional<CustomRequest> getCustomRequestById(Long customRequestId) {
         return Optional.ofNullable(createQuery().select(Q_CUSTOM_REQUEST)
                .from(Q_CUSTOM_REQUEST)
                 .where(Q_CUSTOM_REQUEST.id.eq(customRequestId))
                 .fetchOne());
    }
}
