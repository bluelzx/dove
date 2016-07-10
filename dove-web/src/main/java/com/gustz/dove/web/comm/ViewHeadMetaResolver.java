/*
 * @(#)ViewHeadMetaResolver.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.web.comm;

import javax.servlet.http.HttpServletRequest;

import com.gustz.dove.web.base.view.tpl.ViewHeadMeta;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO: View meta resolver
 *
 * @author ZHENFENG ZHANG
 * @since  [Jun 20, 2015]
 */
public class ViewHeadMetaResolver implements ViewHeadMeta {

    @Autowired
    private HttpServletRequest request;

    /**
     * Get title
     */
    @Override
    public String getTitle() {
        System.out.println("getServletPath=:" + request.getServletPath());
        return "标题";
    }

    /**
     * Get keywords
     */
    @Override
    public String getKeywords() {
        return "关键字";
    }

    /**
     * Get desc
     */
    @Override
    public String getDescription() {
        return "描述";
    }

}
