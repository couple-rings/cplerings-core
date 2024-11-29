package com.cplerings.core.application.design.datasource;

import com.cplerings.core.application.design.datasource.result.Designs;
import com.cplerings.core.application.design.input.ViewDesignsInput;

public interface ViewDesignsDataSource {

    Designs getDesigns(ViewDesignsInput  input);
}
