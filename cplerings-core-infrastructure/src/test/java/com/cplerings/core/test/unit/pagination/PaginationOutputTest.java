package com.cplerings.core.test.unit.pagination;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

class PaginationOutputTest {

    @Test
    void givenPaginationOutput_whenImplementBuilderProperly() {
        final Collection<String> data = Arrays.asList("Hello", "World");

        final DummyPaginationOutput output = thenNoExceptionIsThrownWhenBuilding();
        thenOutputPaginationInfoIsValid(output, data);
    }

    private DummyPaginationOutput thenNoExceptionIsThrownWhenBuilding() {
        try {
            return DummyPaginationOutput.builder()
                    .page(1)
                    .pageSize(20)
                    .totalCount(81)
                    .data(Arrays.asList("Hello", "World"))
                    .build();
        } catch (Exception e) {
            Assertions.fail(e);
            throw new RuntimeException(e);
        }
    }

    private void thenOutputPaginationInfoIsValid(DummyPaginationOutput output, Collection<String> data) {
        Assertions.assertThat(output)
                .isNotNull();
        Assertions.assertThat(output.getPage())
                .isEqualTo(1);
        Assertions.assertThat(output.getPageSize())
                .isEqualTo(20);
        Assertions.assertThat(output.getCount())
                .isEqualTo(data.size());
        Assertions.assertThat(output.getData())
                .containsExactly(data.toArray(new String[0]));
        Assertions.assertThat(output.getTotalPages())
                .isEqualTo((81 / 20) + 1);
    }
}
