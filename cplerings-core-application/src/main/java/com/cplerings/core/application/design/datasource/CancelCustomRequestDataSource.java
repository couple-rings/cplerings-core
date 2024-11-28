package com.cplerings.core.application.design.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestHistory;

public interface CancelCustomRequestDataSource {

    Optional<CustomRequest> getCustomRequestById(Long customRequestId);

    List<DesignVersion> getDesignVersionsByDesignId(Long designId);

    Design save(Design design);

    DesignVersion save(DesignVersion designVersion);

    CustomRequest save(CustomRequest customRequest);

    CustomRequestHistory saveCustomRequestHistory(CustomRequestHistory customRequestHistory);
}
