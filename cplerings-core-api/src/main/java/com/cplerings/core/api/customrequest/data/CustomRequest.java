package com.cplerings.core.api.customrequest.data;

import com.cplerings.core.application.shared.entity.design.ADesign;

import java.util.Set;

public record CustomRequest(Long customRequestId, String comment, String status, Set<ADesign> designs) {
}
