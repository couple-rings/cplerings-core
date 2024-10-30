package com.cplerings.core.application.shared.entity.spouse;

import java.io.Serializable;
import java.util.UUID;

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
public class ASpouse implements Serializable {

    private Long id;
    private String citizenId;
    private UUID coupleId;
}
