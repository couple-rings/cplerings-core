package com.cplerings.core.application.account.datasource.result;

import java.util.List;

import com.cplerings.core.domain.account.Account;

import lombok.Builder;

@Builder
public record Users(List<Account> users) {
}
