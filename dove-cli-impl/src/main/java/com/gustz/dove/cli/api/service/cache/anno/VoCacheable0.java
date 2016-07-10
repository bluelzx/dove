package com.gustz.dove.cli.api.service.cache.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.Cacheable;

/**
 * TODO: VO cacheable
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 29, 2015 ]
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Cacheable(value = "vo_cache")
public @interface VoCacheable0 {

}