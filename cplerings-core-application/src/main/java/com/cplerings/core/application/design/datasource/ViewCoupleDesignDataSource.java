package com.cplerings.core.application.design.datasource;

import com.cplerings.core.application.design.datasource.result.DesignCouples;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;

public interface ViewCoupleDesignDataSource {

    DesignCouples getDesignCouples(ViewCoupleDesignInput input);
}
