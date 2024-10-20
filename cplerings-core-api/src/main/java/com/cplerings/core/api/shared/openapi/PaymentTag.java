package com.cplerings.core.api.shared.openapi;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag(
        name = "Payment",
        description = "APIs for Payment. Mostly used for 3rd party"
)
public @interface PaymentTag {

}
