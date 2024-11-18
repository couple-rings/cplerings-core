package com.cplerings.core.api.agreement.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.agreement.data.AgreementsData;
import com.cplerings.core.api.agreement.request.ViewAgreementsRequest;
import com.cplerings.core.api.agreement.response.ViewAgreementsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.agreement.input.ViewAgreementsInput;
import com.cplerings.core.application.agreement.output.ViewAgreementsOutput;
import com.cplerings.core.application.shared.entity.agreement.AAgreement;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewAgreementsMapper extends APIPaginatedMapper<ViewAgreementsInput, ViewAgreementsOutput, AgreementsData, AAgreement, ViewAgreementsRequest, ViewAgreementsResponse> {
}
