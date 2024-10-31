package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.DesignVersion;

public interface ViewDesignVersionDataSource {

    Optional<DesignVersion> getDesignVersionByUd(long designVersionId);
}
