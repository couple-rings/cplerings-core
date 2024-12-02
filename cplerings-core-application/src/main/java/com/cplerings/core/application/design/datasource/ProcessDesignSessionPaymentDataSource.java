package com.cplerings.core.application.design.datasource;

import java.util.Collection;

import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;

public interface ProcessDesignSessionPaymentDataSource {

    Collection<DesignSession> saveAll(Collection<DesignSession> designSessions);

    CustomRequest save(CustomRequest customRequest);
}
