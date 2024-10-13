package com.cplerings.core.api.mapper;

public interface CustomRequestMapper<CUSTOM_REQ, REQ> {

    REQ map(CUSTOM_REQ customRequest);
}
