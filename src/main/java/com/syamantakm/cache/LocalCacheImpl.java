package com.syamantakm.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author Syamantak Mukhopadhyay
 */
public class LocalCacheImpl implements LocalCache {
    private boolean isCachingRequired;
    private Cache cache;

    public LocalCacheImpl(boolean cachingRequired, Cache cache) {
        isCachingRequired = cachingRequired;
        this.cache = cache;
    }

    @Override
    public <T> T get(Object key, Class<T> tClass) {
        if (cache.isKeyInCache(key)) {
            return (T) cache.get(key).getObjectValue();
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        Element element = new Element(key, value);
        cache.put(element);
    }

    @Override
    public void remove(Object key) {
        cache.remove(key);
    }

    @Override
    public void evict() {
        cache.evictExpiredElements();
    }
}
