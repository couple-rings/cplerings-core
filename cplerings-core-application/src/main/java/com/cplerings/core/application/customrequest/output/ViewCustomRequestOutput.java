package com.cplerings.core.application.customrequest.output;

import java.util.Set;

import com.cplerings.core.application.shared.entity.design.ADesign;

public record ViewCustomRequestOutput(Long customRequestId, String comment, String status, Set<ADesign> designs) {
}
