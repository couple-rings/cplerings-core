package com.cplerings.core.infrastructure.service.configuration.datasource;

import com.cplerings.core.domain.configuration.Configuration;

import java.util.Collection;

public interface ConfigurationServiceDataSource {

    Collection<Configuration> getConfigurations();
}
