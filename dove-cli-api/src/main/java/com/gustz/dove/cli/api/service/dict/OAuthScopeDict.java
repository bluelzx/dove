package com.gustz.dove.cli.api.service.dict;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: OAuth应用授权作用域
 *
 *  <pre>
 *  snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
 *  snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息） 
 *  </pre>
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum OAuthScopeDict implements BaseCliDict {

    /**
     * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
     */
    SNSAPI_BASE("snsapi_base", "直接跳转"),

    /**
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息） 
     */
    SNSAPI_USER_INFO("snsapi_userinfo", "弹出授权页面");

    private final String name;

    private final String value;

    private OAuthScopeDict(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
