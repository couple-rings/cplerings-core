package com.cplerings.core.application.configuration.datasource;

import com.cplerings.core.application.configuration.datasource.data.Configurations;
import com.cplerings.core.application.configuration.input.GetConfigurationsInput;

public interface GetConfigurationsDataSource {

    Configurations getConfigurations(GetConfigurationsInput input);
}
