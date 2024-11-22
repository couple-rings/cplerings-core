package com.cplerings.core.api.agreement.mapper;

import com.cplerings.core.api.agreement.request.SignAgreementRequest;
import com.cplerings.core.api.agreement.response.SignAgreementResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.agreement.input.SignAgreementInput;
import com.cplerings.core.application.agreement.output.SignAgreementOutput;
import com.cplerings.core.application.shared.entity.agreement.AAgreement;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APISignAgreementMapper extends APIMapper<SignAgreementInput, SignAgreementOutput, AAgreement, SignAgreementRequest, SignAgreementResponse> {

    @Override
    @Mapping(target = ".", source = "agreement")
    AAgreement toData(SignAgreementOutput output);
}
