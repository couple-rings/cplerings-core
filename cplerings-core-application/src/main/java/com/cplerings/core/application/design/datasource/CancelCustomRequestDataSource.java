package com.cplerings.core.application.design.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestHistory;
import com.cplerings.core.domain.design.session.DesignSession;

public interface CancelCustomRequestDataSource {

    Optional<CustomRequest> getCustomRequestById(Long customRequestId);

    List<DesignVersion> getDesignVersionsByDesignId(Long designId);

    Design save(Design design);

    DesignVersion save(DesignVersion designVersion);

    CustomRequest save(CustomRequest customRequest);

    CustomRequestHistory saveCustomRequestHistory(CustomRequestHistory customRequestHistory);

    List<DesignSession> getListDesignSession(Long customerId);

    DesignSession save(DesignSession designSession);
}
