package com.cplerings.core.application.design.datasource;

import com.cplerings.core.application.design.datasource.result.DesignCollections;
import com.cplerings.core.application.design.input.ViewDesignCollectionsInput;

public interface ViewDesignCollectionsDataSource {

    DesignCollections getDesignCollections(ViewDesignCollectionsInput input);
}
