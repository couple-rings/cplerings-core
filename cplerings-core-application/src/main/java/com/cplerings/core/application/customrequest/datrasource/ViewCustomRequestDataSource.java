package com.cplerings.core.application.customrequest.datrasource;

import com.cplerings.core.domain.design.request.CustomRequest;

public interface ViewCustomRequestDataSource {

    CustomRequest getCustomRequestById(Long customRequestId);
}
