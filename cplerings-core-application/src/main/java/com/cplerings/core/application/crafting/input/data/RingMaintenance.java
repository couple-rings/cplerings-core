package com.cplerings.core.application.crafting.input.data;

import lombok.Builder;

@Builder
public record RingMaintenance(Long ringId, Long maintenanceDocumentId) {

}
