package com.cplerings.core.application.spouse.input;

import java.time.Instant;

import lombok.Builder;

@Builder
public record PrimarySpouse (String citizenId, Instant dateOfBirth, String fullName, Long customerId) {

}
