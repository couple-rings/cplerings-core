package com.cplerings.core.infrastructure.datasource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Configuration
public class DataSourceConfiguration {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy(value = false)
    public CriteriaBuilderFactory criteriaBuilderFactory() {
        final CriteriaBuilderConfiguration configuration = Criteria.getDefault();
        return configuration.createCriteriaBuilderFactory(entityManagerFactory);
    }
}
