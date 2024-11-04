package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.CreateCustomDesignRequestErrorCode.ACCOUNT_NOT_CUSTOMER;
import static com.cplerings.core.application.design.error.CreateCustomDesignRequestErrorCode.CUSTOMER_NOT_FOUND;
import static com.cplerings.core.application.design.error.CreateCustomDesignRequestErrorCode.DESIGN_NOT_AVAILABLE;
import static com.cplerings.core.application.design.error.CreateCustomDesignRequestErrorCode.INVALID_CUSTOMER_ID;
import static com.cplerings.core.application.design.error.CreateCustomDesignRequestErrorCode.INVALID_DESIGN_ID;
import static com.cplerings.core.application.design.error.CreateCustomDesignRequestErrorCode.MUST_HAVE_TWO_DESIGNS;

import com.cplerings.core.application.design.CreateCustomRequestUseCase;
import com.cplerings.core.application.design.datasource.CreateCustomRequestDataSource;
import com.cplerings.core.application.design.input.CreateCustomRequestInput;
import com.cplerings.core.application.design.mapper.ACreateCustomRequestMapper;
import com.cplerings.core.application.design.output.CreateCustomRequestOutput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.request.DesignCustomRequest;

import lombok.RequiredArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateCustomRequestUseCaseImpl extends AbstractUseCase<CreateCustomRequestInput, CreateCustomRequestOutput>
        implements CreateCustomRequestUseCase {

    private final CreateCustomRequestDataSource dataSource;
    private final ACreateCustomRequestMapper mapper;
    private final SecurityService securityService;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateCustomRequestInput input) {
        super.validateInput(validator, input);
        validateCustomerId(validator, input);
        validator.validate(CollectionUtils.size(input.getDesignIds()) == 2, MUST_HAVE_TWO_DESIGNS);
        validator.validate(input.getDesignIds().stream()
                .allMatch(NumberUtils::isPositive), INVALID_DESIGN_ID);
    }

    private void validateCustomerId(UseCaseValidator validator, CreateCustomRequestInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();
        if (currentUser.role() != Role.CUSTOMER) {
            validator.validate(NumberUtils.isPositive(input.getCustomerId()), INVALID_CUSTOMER_ID);
        }
    }

    @Override
    protected CreateCustomRequestOutput internalExecute(UseCaseValidator validator, CreateCustomRequestInput input) {
        final Account customer = getAccount(input);
        validator.validateAndStopExecution(customer != null, CUSTOMER_NOT_FOUND);
        validator.validateAndStopExecution(customer.getRole() == Role.CUSTOMER, ACCOUNT_NOT_CUSTOMER);
        Collection<Design> designs = dataSource.getAvailableDesignsByIds(input.getDesignIds());
        validator.validateAndStopExecution(CollectionUtils.size(designs) == 2, DESIGN_NOT_AVAILABLE);
        designs.forEach(design -> design.setStatus(DesignStatus.UNAVAILABLE));
        designs = dataSource.saveDesigns(designs);
        CustomRequest customRequest = CustomRequest.builder()
                .customer(customer)
                .status(CustomRequestStatus.PENDING)
                .build();
        customRequest = dataSource.save(customRequest);
        Collection<DesignCustomRequest> designCustomRequests = new ArrayList<>();
        for (Design design : designs) {
            designCustomRequests.add(DesignCustomRequest.builder()
                    .customRequest(customRequest)
                    .design(design)
                    .build());
        }
        designCustomRequests = dataSource.saveDesignCustomRequests(designCustomRequests);
        customRequest.setDesignCustomRequests(new HashSet<>(designCustomRequests));
        return mapper.toOutput(customRequest);
    }

    private Account getAccount(CreateCustomRequestInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();
        if (currentUser.role() == Role.CUSTOMER) {
            return dataSource.getCustomerById(currentUser.id())
                    .orElse(null);
        } else {
            return dataSource.getCustomerById(input.getCustomerId())
                    .orElse(null);
        }
    }
}
