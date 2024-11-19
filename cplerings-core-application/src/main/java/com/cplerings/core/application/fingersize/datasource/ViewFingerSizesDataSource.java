package com.cplerings.core.application.fingersize.datasource;

import com.cplerings.core.application.fingersize.datasource.result.FingerSizes;
import com.cplerings.core.application.fingersize.input.ViewFingerSizesInput;

public interface ViewFingerSizesDataSource {
    FingerSizes getFingerSizes(ViewFingerSizesInput input);
}
