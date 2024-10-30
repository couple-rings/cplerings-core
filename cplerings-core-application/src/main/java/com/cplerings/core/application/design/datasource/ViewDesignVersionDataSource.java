package com.cplerings.core.application.design.datasource;

import java.util.Optional;

public interface ViewDesignVersionDataSource {

    Optional<DesignVersion> getDesignVersionByUd(long designVersionId);
}
