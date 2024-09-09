package com.cplerings.core.api.security;

import com.cplerings.core.common.security.RoleConstant;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Secured(RoleConstant.ROLE_CUSTOMER)
public @interface IsCustomer {

}
