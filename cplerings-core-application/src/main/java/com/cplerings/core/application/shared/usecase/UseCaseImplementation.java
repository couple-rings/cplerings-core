package com.cplerings.core.application.shared.usecase;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Transactional(rollbackOn = Exception.class)
public @interface UseCaseImplementation {

}
