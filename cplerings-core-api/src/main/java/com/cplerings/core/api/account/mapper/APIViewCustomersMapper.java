package com.cplerings.core.api.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.CustomersData;
import com.cplerings.core.api.account.request.ViewCustomersRequest;
import com.cplerings.core.api.account.response.ViewCustomersResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.account.input.ViewCustomersInput;
import com.cplerings.core.application.account.output.ViewCustomersOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomersMapper extends APIPaginatedMapper<ViewCustomersInput, ViewCustomersOutput, CustomersData, AAccount, ViewCustomersRequest, ViewCustomersResponse> {
}
