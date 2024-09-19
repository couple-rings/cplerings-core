package com.cplerings.core.infrastructure.transaction;

import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.transaction.SessionPropagation;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Mapper(config = SpringMapperConfiguration.class)
public interface SessionMapper {

    default TransactionDefinition toDefinition(SessionInformation sessionInformation) {
        final DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setReadOnly(sessionInformation.isReadOnly());
        definition.setTimeout(sessionInformation.getTimeout());
        definition.setPropagationBehavior(toPropagation(sessionInformation.getPropagation()));
        return definition;
    }

    default int toPropagation(SessionPropagation sessionPropagation) {
        return switch (sessionPropagation) {
            case REQUIRED -> TransactionDefinition.PROPAGATION_REQUIRED;
            case SUPPORTS -> TransactionDefinition.PROPAGATION_SUPPORTS;
            case MANDATORY -> TransactionDefinition.PROPAGATION_MANDATORY;
            case REQUIRES_NEW -> TransactionDefinition.PROPAGATION_REQUIRES_NEW;
            case NEVER -> TransactionDefinition.PROPAGATION_NEVER;
            case NESTED -> TransactionDefinition.PROPAGATION_NESTED;
            case NOT_SUPPORTED -> TransactionDefinition.PROPAGATION_NOT_SUPPORTED;
            case null -> TransactionDefinition.PROPAGATION_REQUIRED;
        };
    }
}
