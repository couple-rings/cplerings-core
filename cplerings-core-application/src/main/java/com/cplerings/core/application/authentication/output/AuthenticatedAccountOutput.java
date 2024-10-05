package com.cplerings.core.application.authentication.output;

import com.cplerings.core.application.shared.entity.ARole;

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
public class AuthenticatedAccountOutput {

    private Long id;
    private String email;
    private String password;
    private ARole role;
}
