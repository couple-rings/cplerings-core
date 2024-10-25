package com.cplerings.core.test.unit.pagination;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class PaginationOutputTest {

    static Stream<Arguments> properPaginationArguments() {
        final Stream.Builder<Arguments> builder = Stream.builder();
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(1)
                .pageSize(20)
                .totalCount(81)
                .data(Arrays.asList("1", "2", "3"))
                .expectedTotalPages(5)
                .build()));
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(2)
                .pageSize(10)
                .totalCount(80)
                .data(Arrays.asList("1", "2", "3", "4"))
                .expectedTotalPages(8)
                .build()));
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(0)
                .pageSize(30)
                .totalCount(59)
                .data(Arrays.asList("1", "2", "3", "4", "5"))
                .expectedTotalPages(2)
                .build()));
        return builder.build();
    }

    static Stream<Arguments> invalidPaginationArguments() {
        final Stream.Builder<Arguments> builder = Stream.builder();
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(-1)
                .pageSize(10)
                .totalCount(20)
                .data(Arrays.asList("1", "2", "3"))
                .expectedTotalPages(2)
                .build()));
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(0)
                .pageSize(0)
                .totalCount(20)
                .data(Arrays.asList("1", "2", "3"))
                .expectedTotalPages(2)
                .build()));
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(0)
                .pageSize(-1)
                .totalCount(20)
                .data(Arrays.asList("1", "2", "3"))
                .expectedTotalPages(2)
                .build()));
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(0)
                .pageSize(10)
                .totalCount(-1)
                .data(Arrays.asList("1", "2", "3"))
                .expectedTotalPages(2)
                .build()));
        builder.add(Arguments.of(PaginationTestData.builder()
                .page(0)
                .pageSize(10)
                .totalCount(20)
                .data(null)
                .expectedTotalPages(2)
                .build()));
        return builder.build();
    }

    @ParameterizedTest
    @MethodSource("properPaginationArguments")
    void givenPaginationOutput_whenImplementBuilderProperly(PaginationTestData data) {
        final DummyPaginatedOutput output = thenNoExceptionIsThrownWhenBuilding(data);
        thenOutputPaginationInfoIsValid(output, data);
    }

    private DummyPaginatedOutput thenNoExceptionIsThrownWhenBuilding(PaginationTestData data) {
        try {
            return DummyPaginatedOutput.builder()
                    .page(data.getPage())
                    .pageSize(data.getPageSize())
                    .totalCount(data.getTotalCount())
                    .items(data.getData())
                    .build();
        } catch (Exception e) {
            Assertions.fail(e);
            throw new RuntimeException(e);
        }
    }

    private void thenOutputPaginationInfoIsValid(DummyPaginatedOutput output, PaginationTestData data) {
        Assertions.assertThat(output)
                .isNotNull();
        Assertions.assertThat(output.getPage())
                .isEqualTo(data.getPage());
        Assertions.assertThat(output.getPageSize())
                .isEqualTo(data.getPageSize());
        Assertions.assertThat(output.getCount())
                .isEqualTo(data.getData().size());
        Assertions.assertThat(output.getItems())
                .containsExactly(data.getData().toArray(String[]::new));
        Assertions.assertThat(output.getTotalPages())
                .isEqualTo(data.getExpectedTotalPages());
    }

    @ParameterizedTest
    @MethodSource("invalidPaginationArguments")
    void givenPaginationOutput_whenInputIsInvalid(PaginationTestData data) {
        thenExceptionIsThrown(data);
    }

    private void thenExceptionIsThrown(PaginationTestData data) {
        Assertions.assertThatException()
                .isThrownBy(() -> DummyPaginatedOutput.builder()
                        .page(data.getPage())
                        .pageSize(data.getPageSize())
                        .totalCount(data.getTotalCount())
                        .items(data.getData())
                        .totalCount(data.getTotalCount())
                        .build());
    }
}
