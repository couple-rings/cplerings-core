package com.cplerings.core.application.shared.service.configuration;

import com.cplerings.core.domain.configuration.ConfigurationStatus;
import com.cplerings.core.domain.shared.valueobject.Money;

public record MoneyConfiguration(Money money, ConfigurationStatus status) {

}
