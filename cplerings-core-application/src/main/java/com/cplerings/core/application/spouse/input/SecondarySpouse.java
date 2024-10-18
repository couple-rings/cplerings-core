package com.cplerings.core.application.spouse.input;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SecondarySpouse(String citizenId, Instant dateOfBirth, String fullName) {

}
