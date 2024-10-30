package com.cplerings.core.application.shared.entity.account;

import java.io.Serializable;

import com.cplerings.core.domain.account.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AAccount implements Serializable {

    private Long id;
    private String email;
    private String username;
    private String phone;
    private String avatar;
    private Role role;
}
