package com.cplerings.core.application.customrequest.datrasource;

import java.util.Optional;

import com.cplerings.core.domain.design.request.CustomRequest;

public interface ViewCustomRequestDataSource {

    Optional<CustomRequest> getCustomRequestById(Long customRequestId);
}
