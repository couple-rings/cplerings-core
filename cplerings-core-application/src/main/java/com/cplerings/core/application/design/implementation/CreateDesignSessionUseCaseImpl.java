package com.cplerings.core.application.design.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cplerings.core.application.design.CreateDesignSessionUseCase;
import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.application.design.error.DesignErrorCode;
import com.cplerings.core.application.design.input.CreateDesignSessionInput;
import com.cplerings.core.application.design.mapper.ACreateDesignSessionMapper;
import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.DesignSession;
import com.cplerings.core.domain.design.DesignSessionStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateDesignSessionUseCaseImpl extends AbstractUseCase<CreateDesignSessionInput, CreateDesignSessionOutput>
        implements CreateDesignSessionUseCase {

    private final CreateDesignSessionDataSource createDesignSessionDataSource;
    private final ACreateDesignSessionMapper aCreateDesignSessionMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateDesignSessionInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customerId() > 0, DesignErrorCode.INVALID_CUSTOMER_ID);
    }

    @Override
    protected CreateDesignSessionOutput internalExecute(UseCaseValidator validator, CreateDesignSessionInput input) {
        List<DesignSession> designSessions = new ArrayList<>();
        // Check customer is real or not
        Account customer = createDesignSessionDataSource.getAccountById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, DesignErrorCode.NOT_FOUND_CUSTOMER);
        UUID sessionId = UUID.randomUUID();
        DesignSession designSession = DesignSession.builder()
                .sessionId(sessionId)
                .status(DesignSessionStatus.UNUSED)
                .customer(customer)
                .build();

        for (int i = 0; i < 3; i++) {
            designSessions.add(designSession);
        }

        createDesignSessionDataSource.createDesignSession(designSessions);
        return aCreateDesignSessionMapper.toOutput(sessionId);
    }
}
