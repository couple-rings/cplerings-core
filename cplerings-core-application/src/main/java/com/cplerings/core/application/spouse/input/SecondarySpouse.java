package com.cplerings.core.application.spouse.input;

import java.time.Instant;

import lombok.Builder;

@Builder
public record SecondarySpouse (String citizenId, Instant dateOfBirth, String fullName) {

}
