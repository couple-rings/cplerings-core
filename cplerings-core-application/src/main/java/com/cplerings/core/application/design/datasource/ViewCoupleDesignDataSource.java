package com.cplerings.core.application.design.datasource;

import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.queryoutput.DesignCouples;

public interface ViewCoupleDesignDataSource {

    DesignCouples getDesignCouples(ViewCoupleDesignInput viewCoupleDesignInput);
}
