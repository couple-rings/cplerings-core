package com.cplerings.core.api.dev.mapper;

import com.cplerings.core.api.dev.request.GenerateVNPayPaymentLinkRequest;
import com.cplerings.core.api.dev.response.GenerateVNPayPaymentLinkResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dev.input.GenerateVNPayPaymentLinkInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGenerateVNPayPaymentLinkMapper extends APIMapper<GenerateVNPayPaymentLinkInput, String, String, GenerateVNPayPaymentLinkRequest, GenerateVNPayPaymentLinkResponse> {

    @Override
    default String toData(String output) {
        return output;
    }
}
