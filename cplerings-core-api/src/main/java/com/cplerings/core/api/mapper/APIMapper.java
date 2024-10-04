package com.cplerings.core.api.mapper;

@SuppressWarnings("java:S119")
public interface APIMapper<IN, OUT, DATA, REQ, RES> {

    IN toInput(REQ request);

    DATA toData(OUT output);

    RES toResponse(DATA data);
}
