package com.cplerings.core.api.shared.mapper;

import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.application.shared.input.NoInput;

public interface APINoRequestMapper<OUT, DATA, RES> extends APIMapper<NoInput, OUT, DATA, NoRequest, RES> {

    @Override
    default NoInput toInput(NoRequest request) {
        return NoInput.INSTANCE;
    }
}
