package com.cplerings.core.infrastructure.datasource.configuration;

import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.configuration.datasource.GetConfigurationsDataSource;
import com.cplerings.core.application.configuration.datasource.data.Configurations;
import com.cplerings.core.application.configuration.input.GetConfigurationsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.QConfiguration;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class SharedConfigurationDataSource extends AbstractDataSource implements GetConfigurationsDataSource {

    private static final QConfiguration Q_CONFIGURATION = QConfiguration.configuration;

    @Override
    public Configurations getConfigurations(GetConfigurationsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Configuration> query = createQuery()
                .select(Q_CONFIGURATION)
                .from(Q_CONFIGURATION);
        long count = query.distinct().fetchCount();
        List<Configuration> configurations = query.limit(input.getPageSize()).offset(offset).fetch();
        return Configurations.builder()
                .configurations(configurations)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
