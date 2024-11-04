package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.design.request.CustomRequest;

import java.util.Optional;

public interface ViewCustomRequestDataSource {

    Optional<CustomRequest> getCustomRequestById(Long customRequestId);
}
