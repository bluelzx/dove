package com.gustz.dove.cli.api.service.cache.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CacheEvict;

/**
 * TODO: VO cacheable p0
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 29, 2015 ]
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@CacheEvict(value = "vo_cache", key = "#p0")
public @interface VoCacheableP0 {

}