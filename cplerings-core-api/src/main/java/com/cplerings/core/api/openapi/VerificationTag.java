package com.cplerings.core.api.openapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.tags.Tag;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag(
        name = "Verification",
        description = "APIs for resend verification"
)
public @interface VerificationTag {
}
