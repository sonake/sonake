package com.hc.admin.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
