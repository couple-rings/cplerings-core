package com.cplerings.core.api.account.data;

import lombok.Builder;

@Builder
public record ProfileData(Long id, String email, String username, String phone, String avatar, Boolean hasSpouse) {

}
