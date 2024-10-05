package com.cplerings.core.api.shared;

import com.cplerings.core.common.pagination.Pageable;

public abstract class AbstractPaginatedController<IN, OUT, DATA extends Pageable, REQ, RES>
        extends AbstractController<IN, OUT, DATA, REQ, RES> {

}
