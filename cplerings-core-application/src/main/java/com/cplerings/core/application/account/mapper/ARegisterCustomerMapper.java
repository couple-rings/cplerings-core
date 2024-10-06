package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.account.input.RegisterCustomerInput;
import com.cplerings.core.application.account.output.CustomerRegistrationOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

@Mapper(config = SpringMapperConfiguration.class)
public interface ARegisterCustomerMapper {

    CustomerRegistrationOutput toOutput(Account account);

    @Mapping(target = "password", source = "password")
    Account toAccount(RegisterCustomerInput input, String password);
}
