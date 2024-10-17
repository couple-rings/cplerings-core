package com.cplerings.core.api.shared.mapper;

import com.cplerings.core.common.pagination.Pageable;

public interface APIPaginatedMapper<IN, OUT, DATA extends Pageable, REQ, RES>
        extends APIMapper<IN, OUT, DATA, REQ, RES> {

}
