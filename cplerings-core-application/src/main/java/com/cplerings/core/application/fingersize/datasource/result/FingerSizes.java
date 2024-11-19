package com.cplerings.core.application.fingersize.datasource.result;

import java.util.List;

import com.cplerings.core.domain.ring.FingerSize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FingerSizes {

    private List<FingerSize> fingerSizes;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
