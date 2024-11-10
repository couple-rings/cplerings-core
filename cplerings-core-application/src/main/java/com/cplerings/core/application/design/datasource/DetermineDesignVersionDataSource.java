package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;

public interface DetermineDesignVersionDataSource {

    DesignVersion acceptDesignVersion(DesignVersion designVersion);
    Optional<DesignVersion> getDesignVersionById(long designVersionId);
    void updateCustomRequest(CustomRequest customRequest);
}
