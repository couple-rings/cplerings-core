package com.cplerings.core.api.fingersize.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.ring.AFingerSize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FingerSizesData extends AbstractPaginatedData<AFingerSize> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, FingerSizesData, AFingerSize> {

        @Override
        protected FingerSizesData getDataInstance() {
            return new FingerSizesData();
        }
    }
}
