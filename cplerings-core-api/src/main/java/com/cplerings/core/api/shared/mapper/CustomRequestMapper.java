package com.cplerings.core.api.shared.mapper;

public interface CustomRequestMapper<CUSTOM_REQ, REQ> {

    REQ map(CUSTOM_REQ customRequest);
}
