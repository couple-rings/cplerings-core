package com.cplerings.core.application.account.datasource.result;

import java.util.List;

import com.cplerings.core.domain.account.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Jewelers {

    private List<Account> jewelers;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
