package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.design.DesignVersion;

public interface UpdateDesignVersionStatusDataSource {

    DesignVersion acceptDesignVersion(DesignVersion designVersion);
}
