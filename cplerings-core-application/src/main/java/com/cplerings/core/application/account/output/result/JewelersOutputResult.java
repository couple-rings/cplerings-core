package com.cplerings.core.application.account.output.result;

import java.util.List;

import com.cplerings.core.application.shared.entity.account.AJeweler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JewelersOutputResult {

    private List<AJeweler> jewelers;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
