package com.cplerings.core.application.account.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCustomersInput extends AbstractPaginatedInput {

    private String email;

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    @NoArgsConstructor
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCustomersInput> {

        private String email;

        public Builder email(String email) {
            this.email = email;
            return self();
        }

        @Override
        public ViewCustomersInput build() {
            final ViewCustomersInput input = super.build();
            input.setEmail(email);
            return input;
        }

        @Override
        protected ViewCustomersInput getRequestInstance() {
            return new ViewCustomersInput();
        }
    }
}
