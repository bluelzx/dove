/*
 * @(#)EhcacheServiceImpl.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cli.api.comm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.CacheService;

/**
 * TODO: Ehcache service impl
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 29, 2015 ]
 */
@Deprecated
@Service
public class EhcacheServiceImpl<K, V> implements CacheService<K, V> {

    @Autowired
    private EhCacheCacheManager cacheManager;

    /**
     * Init config
     */
    // @PostConstruct
    void init() {
        try {

        } catch (Exception e) {
            throw new Error("Init ehcache is fail. \n", e);
        }
    }

    //@PreDestroy
    void dostory() {
        try {
        } catch (Exception e) {
            throw new Error("Dostory ehcache is fail. \n", e);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public long getDefaultExpire() {
        return 0;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void put(Object key, Object value, long expire) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void remove(Object key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int eliminate() {
        return 0;
    }

}
