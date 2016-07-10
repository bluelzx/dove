package com.gustz.dove.cli.api.service;

/**
 * 
 * TODO: Base web service URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public interface BaseWebsUrl {

    /**
     * code
     */
    public static final String CODE_PARAM = "code";

    /**
     * state
     */
    public static final String STATE_PARAM = "state";

    /**
     * oauthCbUrl
     */
    public static final String OAUTH_CBURL_PARAM = "oauthCbUrl";

    /**
     * OAuth授权回调URL参数KEY oauthCbUrl=
     */
    public static final String OAUTH_CBURL_KEY = OAUTH_CBURL_PARAM + "=";

    /**
     * &oauthCbUrl=
     */
    public static final String OAUTH_CBURL_KEY_EXT = "&" + OAUTH_CBURL_KEY;

    /**
     * ?oauthCbUrl=
     */
    public static final String OAUTH_CBURL_KEY_EXT2 = "?" + OAUTH_CBURL_KEY;

    /**
     * OAuth授权回调URL参数pattern {OAUTH_CB_URL}
     */
    public static final String OAUTH_CBURL_PATT = "{OAUTH_CBURL}";

    /**
     * Get web service code
     * 
     * @return
     */
    String getWebsCode();

    /**
     * Get URL
     * 
     * @return
     */
    String getUrl();

    /**
     * Get text
     * 
     * @return
     */
    String getText();

    /**
     * Set URL
     * 
     * @param url
     * 
     * @return
     */
    void setUrl(String url);

    /**
     * Set text
     * 
     * @param text
     * 
     * @return
     */
    void setText(String text);

}
