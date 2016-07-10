package com.gustz.dove.cli.api.service.cache.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CacheEvict;

/**
 * TODO: VO cache evict
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 29, 2015 ]
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@CacheEvict(value = "vo_cache", beforeInvocation = true)
public @interface VoCacheEvict0 {

}