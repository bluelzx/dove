package com.gustz.dove.cli.api.service.cache.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.Cacheable;

/**
 * TODO: AccessToken cacheable p1_p2
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 29, 2015 ]
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Cacheable(value = "ac_token_cache", key = "#p1+'_'+#p2")
public @interface AcTokenCacheP1p2 {

}