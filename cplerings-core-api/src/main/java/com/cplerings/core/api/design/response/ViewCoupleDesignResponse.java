package com.cplerings.core.api.design.response;

import java.util.List;

import com.cplerings.core.api.design.data.DesignCoupleInformation;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewCoupleDesignResponse extends AbstractDataResponse<List<DesignCoupleInformation>> {

    private int page;
    private int pageSize;
    private int totalPages;
    private long count;
    private final Type type = Type.PAGINATED_DATA;
}
