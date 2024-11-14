package com.cplerings.core.application.spouse.implementation;

import java.util.List;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.spouse.ViewSpousesOfCustomerUseCase;
import com.cplerings.core.application.spouse.datasource.ViewSpousesOfCustomerDataSource;
import com.cplerings.core.application.spouse.datasource.result.SpouseList;
import com.cplerings.core.application.spouse.error.ViewSpousesOfCustomerErrorCode;
import com.cplerings.core.application.spouse.input.ViewSpousesOfCustomerInput;
import com.cplerings.core.application.spouse.mapper.AViewSpousesOfCustomerMapper;
import com.cplerings.core.application.spouse.output.ViewSpousesOfCustomerOutput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.spouse.Spouse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewSpousesOfCustomerUseCaseImpl extends AbstractUseCase<ViewSpousesOfCustomerInput, ViewSpousesOfCustomerOutput> implements ViewSpousesOfCustomerUseCase {

    private final ViewSpousesOfCustomerDataSource viewSpousesOfCustomerDataSource;
    private final AViewSpousesOfCustomerMapper aViewSpousesOfCustomerMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewSpousesOfCustomerInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customerId() != null, ViewSpousesOfCustomerErrorCode.CUSTOMER_ID_REQUIRED);
        validator.validateAndStopExecution(input.customerId() > 0, ViewSpousesOfCustomerErrorCode.CUSTOMER_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected ViewSpousesOfCustomerOutput internalExecute(UseCaseValidator validator, ViewSpousesOfCustomerInput input) {
        Account customer = viewSpousesOfCustomerDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, ViewSpousesOfCustomerErrorCode.INVALID_CUSTOMER_ID);
        validator.validateAndStopExecution(customer.getRole() == Role.CUSTOMER, ViewSpousesOfCustomerErrorCode.NOT_A_CUSTOMER);
        Spouse spouse = viewSpousesOfCustomerDataSource.getSpousesByCustomerId(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(spouse != null, ViewSpousesOfCustomerErrorCode.CUSTOMER_NOT_HAVE_ANY_SPOUSE);
        List<Spouse> spouses = viewSpousesOfCustomerDataSource.getSpouseByCoupleId(spouse.getCoupleId());
        SpouseList spouseList = SpouseList.builder().spouses(spouses).build();
        return aViewSpousesOfCustomerMapper.toOutput(spouseList);
    }
}
