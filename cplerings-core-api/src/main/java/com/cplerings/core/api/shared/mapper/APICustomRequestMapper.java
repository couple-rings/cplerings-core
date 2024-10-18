package com.cplerings.core.api.shared.mapper;

public interface APICustomRequestMapper<CUSTOM_REQ, REQ> {

    REQ map(CUSTOM_REQ customRequest);
}
