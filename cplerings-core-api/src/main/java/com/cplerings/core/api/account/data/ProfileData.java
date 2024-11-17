package com.cplerings.core.api.account.data;

import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Builder;

@Builder
public record ProfileData(AAccount account, Boolean hasSpouse) {

}
