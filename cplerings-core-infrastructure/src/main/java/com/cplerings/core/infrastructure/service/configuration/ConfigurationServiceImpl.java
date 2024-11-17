package com.cplerings.core.infrastructure.service.configuration;

import com.cplerings.core.application.shared.service.configuration.ConfigurationKey;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.ConfigurationStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.service.configuration.datasource.ConfigurationServiceDataSource;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationServiceDataSource configurationServiceDataSource;

    @Override
    public Money getDesignFee() {
        return getConfiguration(ConfigurationKey.DEFE.getKey(), Money.class);
    }

    @Override
    public Money getSideDiamondPrice() {
        return getConfiguration(ConfigurationKey.SDPR.getKey(), Money.class);
    }

    @Override
    public Integer getCraftingStageProgress1() {
        return getConfiguration(ConfigurationKey.CSP1.getKey(), Integer.class);
    }

    @Override
    public Integer getCraftingStageProgress2() {
        return getConfiguration(ConfigurationKey.CSP2.getKey(), Integer.class);
    }

    @Override
    public Integer getCraftingStageProgress3() {
        return getConfiguration(ConfigurationKey.CSP3.getKey(), Integer.class);
    }

    @Override
    public Integer getMaximumMaintenanceDuration() {
        return getConfiguration(ConfigurationKey.MMAD.getKey(), Integer.class);
    }

    private <T> T getConfiguration(String key, Class<T> clazz) {
        if (StringUtils.isEmpty(key) || clazz == null) {
            throw new IllegalArgumentException("key or clazz is null");
        }
        final Configuration configuration = configurationServiceDataSource.getConfigurations()
                .stream()
                .filter(c -> StringUtils.equals(c.getKey(), key) && c.getStatus() == ConfigurationStatus.ACTIVE)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot find configuration for " + key));
        return mapResult(configuration.getValue(), clazz);
    }

    @SuppressWarnings("unchecked")
    private <T> T mapResult(String value, Class<T> clazz) {
        if (Objects.equals(clazz, String.class)) {
            return (T) value;
        }
        if (Objects.equals(clazz, Money.class)) {
            return (T) Money.create(BigDecimal.valueOf(Double.parseDouble(value)));
        }
        if (Objects.equals(clazz, Integer.class)) {
            return (T) Integer.valueOf(Integer.parseInt(value));
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz);
    }
}
