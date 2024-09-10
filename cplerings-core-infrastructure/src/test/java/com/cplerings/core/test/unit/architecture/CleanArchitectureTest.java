package com.cplerings.core.test.unit.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(
        packages = {
                "com.cplerings.core.api..",
                "com.cplerings.core.application..",
                "com.cplerings.core.domain..",
                "com.cplerings.core.infrastructure..",
                "com.cplerings.core.common..",
        }
)
class CleanArchitectureTest {

    private static final String DOMAIN_LAYER = "Domain Layer";
    private static final String DOMAIN_LAYER_PATH = "com.cplerings.core.domain..";

    private static final String APPLICATION_LAYER = "Application Layer";
    private static final String APPLICATION_LAYER_PATH = "com.cplerings.core.application..";

    private static final String API_LAYER = "API Layer";
    private static final String API_LAYER_PATH = "com.cplerings.core.api..";

    private static final String INFRASTRUCTURE_LAYER = "Infrastructure Layer";
    private static final String INFRASTRUCTURE_LAYER_PATH = "com.cplerings.core.infrastructure..";

    private static final String COMMON_LAYER = "Common Layer";
    private static final String COMMON_LAYER_PATH = "com.cplerings.core.common..";

    @ArchTest
    void givenProject_whenEveryoneCode_thenCodeShouldAdhereToCleanArchitecture(JavaClasses javaClasses) {
        Architectures.layeredArchitecture()
                .consideringOnlyDependenciesInLayers()
                .layer(DOMAIN_LAYER).definedBy(DOMAIN_LAYER_PATH)
                .layer(APPLICATION_LAYER).definedBy(APPLICATION_LAYER_PATH)
                .layer(API_LAYER).definedBy(API_LAYER_PATH)
                .layer(INFRASTRUCTURE_LAYER).definedBy(INFRASTRUCTURE_LAYER_PATH)
                .optionalLayer(COMMON_LAYER).definedBy(COMMON_LAYER_PATH)
                .whereLayer(DOMAIN_LAYER).mayOnlyAccessLayers(COMMON_LAYER)
                .whereLayer(DOMAIN_LAYER).mayOnlyBeAccessedByLayers(APPLICATION_LAYER, INFRASTRUCTURE_LAYER)
                .whereLayer(APPLICATION_LAYER).mayOnlyAccessLayers(DOMAIN_LAYER, COMMON_LAYER)
                .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(API_LAYER, INFRASTRUCTURE_LAYER)
                .whereLayer(API_LAYER).mayOnlyAccessLayers(APPLICATION_LAYER, COMMON_LAYER)
                .whereLayer(API_LAYER).mayOnlyBeAccessedByLayers(INFRASTRUCTURE_LAYER)
                .whereLayer(INFRASTRUCTURE_LAYER).mayOnlyAccessLayers(API_LAYER, APPLICATION_LAYER, DOMAIN_LAYER, COMMON_LAYER)
                .whereLayer(INFRASTRUCTURE_LAYER).mayNotBeAccessedByAnyLayer()
                .whereLayer(COMMON_LAYER).mayNotAccessAnyLayer()
                .check(javaClasses);
    }
}
