package com.cplerings.core.infrastructure.datasource.service.configuration;

import java.util.Collection;

import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.ConfigurationStatus;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.ConfigurationRepository;
import com.cplerings.core.infrastructure.service.configuration.datasource.ConfigurationServiceDataSource;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class ConfigurationServiceDataSourceImpl extends AbstractDataSource implements ConfigurationServiceDataSource {

    private final ConfigurationRepository configurationRepository;

    @Override
    public Collection<Configuration> getConfigurations() {
        return configurationRepository.findAllByStatus(ConfigurationStatus.ACTIVE);
    }
}
