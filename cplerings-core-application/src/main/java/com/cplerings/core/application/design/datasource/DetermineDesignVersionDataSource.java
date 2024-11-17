package com.cplerings.core.application.design.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;

public interface DetermineDesignVersionDataSource {

    DesignVersion acceptDesignVersion(DesignVersion designVersion);
    Optional<DesignVersion> getDesignVersionById(long designVersionId);
    void updateCustomRequest(CustomRequest customRequest);
    List<DesignVersion> getDesignVersionRemainingByDesignId(Long designId, Long designVersionId);
    DesignVersion save(DesignVersion designVersion);
}
