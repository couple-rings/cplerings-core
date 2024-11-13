package com.cplerings.core.api.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.TransportersData;
import com.cplerings.core.api.account.request.ViewTransportersRequest;
import com.cplerings.core.api.account.response.ViewTransportersResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.account.input.ViewTransportersInput;
import com.cplerings.core.application.account.output.ViewTransportersOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTransportersMapper extends APIPaginatedMapper<ViewTransportersInput, ViewTransportersOutput, TransportersData, AAccount, ViewTransportersRequest, ViewTransportersResponse> {
}
