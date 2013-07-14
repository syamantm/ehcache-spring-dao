package com.syamantakm.cache;

/**
 * @author Syamantak Mukhopadhyay
 */
public interface LocalCache {
    <T> T get(Object key, Class<T> tClass);

    void put(Object key, Object value);

    void remove(Object key);

    void evict();
}
