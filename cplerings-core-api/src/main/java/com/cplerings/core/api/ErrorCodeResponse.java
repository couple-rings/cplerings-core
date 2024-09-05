package com.cplerings.core.api;

import java.util.Comparator;
import java.util.Objects;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorCodeResponse implements Comparable<ErrorCodeResponse> {

    public enum Type {

        VALIDATION, BUSINESS, SYSTEM
    }

    private String code;
    private String description;
    private Type type;

    @Override
    public int compareTo(@Nonnull ErrorCodeResponse o) {
        return Objects.compare(code, o.code, Comparator.naturalOrder());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorCodeResponse that = (ErrorCodeResponse) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
