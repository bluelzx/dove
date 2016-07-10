package com.gustz.dove.cli.api.service;

/**
 * TODO: Cache service I
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public interface CacheService<K, V> {

    /**
     * Cache map size
     * 
     * @return  
     */
    int size();

    /**
     * Get default expire
     * <pre>
     * unit: ms
     * </pre>
     * @return
     */
    long getDefaultExpire();

    /**
     * Put to cache
     * 
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * Put to cache
     * 
     * @param key
     * @param value
     * @param expire unit:ms
     */
    void put(K key, V value, long expire);

    /**
     * Get from cache
     * 
     * @param key
     * @return
     */
    V get(K key);

    /**
     * Cache is full
     * 
     * @return
     */
    boolean isFull();

    /**
     * Remove from cache
     * 
     * @param key
     */
    void remove(K key);

    /**
     * Clear all from cache
     */
    void clear();

    /**
     * Get custom cache size
     * 
     * @return  
     */
    int getCacheSize();

    /**
     * Cache map is empty
     */
    boolean isEmpty();

    /**
     * eliminate object
     * 
     * @return
     */
    int eliminate();

}