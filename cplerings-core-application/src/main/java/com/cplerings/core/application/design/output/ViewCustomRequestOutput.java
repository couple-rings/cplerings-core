package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ADesign;

import java.util.Set;

public record ViewCustomRequestOutput(Long customRequestId, String comment, String status, Set<ADesign> designs) {

}
