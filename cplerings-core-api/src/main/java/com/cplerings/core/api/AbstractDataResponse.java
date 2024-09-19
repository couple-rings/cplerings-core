package com.cplerings.core.api;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Objects;

@Slf4j
@Getter
@Setter
public abstract class AbstractDataResponse<T> extends AbstractResponse {

    private T data;

    public static <T, R extends AbstractDataResponse<T>> R create(T data, Class<R> clazz) {
        Objects.requireNonNull(data, "Data must not be null");
        Objects.requireNonNull(clazz, "Response class must not be null");
        if (Objects.equals(clazz, AbstractDataResponse.class)) {
            throw new IllegalArgumentException("Response class must be extension of AbstractDataResponse");
        }
        final boolean originalAccessible;
        final Constructor<R> constructor;
        try {
            constructor = clazz.getConstructor();
            originalAccessible = constructor.canAccess(null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("There must be empty constructor in response class", e);
        }
        try {
            constructor.setAccessible(true);
            final R response = constructor.newInstance();
            response.setData(data);
            response.setType(Type.DATA);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            constructor.setAccessible(originalAccessible);
        }
    }
}
