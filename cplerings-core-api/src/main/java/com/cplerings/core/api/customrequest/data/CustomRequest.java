package com.cplerings.core.api.customrequest.data;

import java.util.Set;

import com.cplerings.core.application.shared.entity.design.ADesign;

public record CustomRequest(Long customRequestId, String comment, String status, Set<ADesign> designs) {
}
