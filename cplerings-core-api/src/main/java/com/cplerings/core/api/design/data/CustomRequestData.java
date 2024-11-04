package com.cplerings.core.api.design.data;

import com.cplerings.core.application.shared.entity.design.ADesign;

import java.util.Set;

public record CustomRequestData(Long customRequestId, String comment, String status, Set<ADesign> designs) {

}
