package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.CreateTransportationAddressUseCase;
import com.cplerings.core.application.transport.datasource.CreateTransportationAddressDataSource;
import com.cplerings.core.application.transport.error.AssignTransportOrderErrorCode;
import com.cplerings.core.application.transport.error.CreateTransportationAddressErrorCode;
import com.cplerings.core.application.transport.input.AssignTransportOrderInput;
import com.cplerings.core.application.transport.input.CreateTransportationAddressInput;
import com.cplerings.core.application.transport.mapper.ACreateTransportationAddressMapper;
import com.cplerings.core.application.transport.output.CreateTransportationAddressOutput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.address.TransportationAddress;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateTransportationAddressUseCaseImpl extends AbstractUseCase<CreateTransportationAddressInput, CreateTransportationAddressOutput> implements CreateTransportationAddressUseCase {

    private final CreateTransportationAddressDataSource createTransportationAddressDataSource;
    private final ACreateTransportationAddressMapper aCreateTransportationAddressMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateTransportationAddressInput input) {
        super.validateInput(validator, input);
        validator.validate(input.address() != null, CreateTransportationAddressErrorCode.ADDRESS_REQUIRED);
        validator.validate(input.receiverPhone() != null, CreateTransportationAddressErrorCode.RECEIVER_PHONE_NUMBER_REQUIRED);
        validator.validate(input.receiverName() != null, CreateTransportationAddressErrorCode.RECEIVER_NAME_REQUIRED);
        validator.validate(input.districtCode() != null, CreateTransportationAddressErrorCode.DISTRICT_CODE_REQUIRED);
        validator.validate(input.district() != null, CreateTransportationAddressErrorCode.DISTRICT_REQUIRED);
        validator.validate(input.ward() != null, CreateTransportationAddressErrorCode.WARD_REQUIRED);
        validator.validate(input.wardCode() != null, CreateTransportationAddressErrorCode.WARD_CODE_REQUIRED);
        validator.validate(input.customerId() != null, CreateTransportationAddressErrorCode.CUSTOMER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.customerId() > 0, CreateTransportationAddressErrorCode.CUSTOMER_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected CreateTransportationAddressOutput internalExecute(UseCaseValidator validator, CreateTransportationAddressInput input) {
        Account customer = createTransportationAddressDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CreateTransportationAddressErrorCode.INVALID_CUSTOMER_ID);
        TransportationAddress transportationAddress = TransportationAddress.builder()
                .address(input.address())
                .customer(customer)
                .district(input.district())
                .districtCode(input.districtCode().toString())
                .receiverName(input.receiverName())
                .receiverPhone(input.receiverPhone())
                .ward(input.ward())
                .wardCode(input.wardCode().toString())
                .build();
        TransportationAddress transportationAddressCreated = createTransportationAddressDataSource.save(transportationAddress);
        return aCreateTransportationAddressMapper.toOutput(transportationAddressCreated);
    }
}
