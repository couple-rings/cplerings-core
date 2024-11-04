package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.design.DesignVersion;

import java.util.Optional;

public interface ViewDesignVersionDataSource {

    Optional<DesignVersion> getDesignVersionById(long designVersionId);
}
