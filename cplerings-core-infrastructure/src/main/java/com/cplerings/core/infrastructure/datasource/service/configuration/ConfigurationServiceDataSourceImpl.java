package com.cplerings.core.infrastructure.datasource.service.configuration;

import com.cplerings.core.common.cache.CacheConstant;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.ConfigurationStatus;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.ConfigurationRepository;
import com.cplerings.core.infrastructure.service.configuration.datasource.ConfigurationServiceDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;

@DataSource
@RequiredArgsConstructor
public class ConfigurationServiceDataSourceImpl extends AbstractDataSource implements ConfigurationServiceDataSource {

    private final ConfigurationRepository configurationRepository;

    @Override
    @Cacheable(CacheConstant.CONFIGURATION_CACHE)
    public Collection<Configuration> getConfigurations() {
        return configurationRepository.findAllByStatus(ConfigurationStatus.ACTIVE);
    }
}
