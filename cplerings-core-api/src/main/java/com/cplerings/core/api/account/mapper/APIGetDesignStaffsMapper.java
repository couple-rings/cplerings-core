package com.cplerings.core.api.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.DesignStaffsData;
import com.cplerings.core.api.account.request.GetDesignStaffsRequest;
import com.cplerings.core.api.account.response.GetDesignStaffsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.account.input.GetDesignStaffsInput;
import com.cplerings.core.application.account.output.GetDesignStaffsOutput;
import com.cplerings.core.application.shared.entity.account.ADesignStaff;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetDesignStaffsMapper extends APIPaginatedMapper<GetDesignStaffsInput, GetDesignStaffsOutput, DesignStaffsData, ADesignStaff, GetDesignStaffsRequest, GetDesignStaffsResponse> {
}
