package com.cplerings.core.application.transport.datasource.result;

import java.util.List;

import com.cplerings.core.domain.order.status.TransportationNote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransportationNotes {

    private List<TransportationNote> transportationNotes;
    private long count;
    private int page;
    private int pageSize;
}
