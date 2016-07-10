package com.gustz.dove.web.base;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.fw.api.vo.AbstractBaseVo;
import com.sinovatech.fw.mvc.BaseController;

/**
 * Ext Controller基类
 * 
 * @param <SF>
 *            SearchForm 查询载体
 * @param <BEAN>
 *            VO对象载体
 */
public abstract class ExtBaseController<SF, BEAN extends AbstractBaseVo<String>> extends BaseController<SF, BEAN> {

    @Autowired
    private ExtPtBaseController extPtBaseController;

    /**
     * Forward DYNC page
     * 
     * @param request
     * @throws Exception
     */
    protected String forward(HttpServletRequest request) throws Exception {
        return extPtBaseController.forward(request, null);
    }

    /**
     * Forward DYNC page
     * 
     * @param request
     * @param dataMap
     * @throws Exception
     */
    protected String forward(HttpServletRequest request, Map<String, ?> dataMap) throws Exception {
        return extPtBaseController.forward(request, dataMap);
    }

    /**
     * Set MS excel attachment type
     *
     * @param request
     * @param response
     * @param filenamePrefix
     * @throws UnsupportedEncodingException 
     */
    protected void setMsXlsAttaType(HttpServletRequest request, HttpServletResponse response, String filenamePrefix)
            throws UnsupportedEncodingException {
        extPtBaseController.setMsXlsAttaType(request, response, filenamePrefix);
    }

    /**
     * Set MS word attachment type
     *
     * @param request
     * @param response
     * @param filenamePrefix 
     * @throws UnsupportedEncodingException 
     */
    protected void setMsDocAttaType(HttpServletRequest request, HttpServletResponse response, String filenamePrefix)
            throws UnsupportedEncodingException {
        extPtBaseController.setMsDocAttaType(request, response, filenamePrefix);
    }

    /**
     * Set PDF attachment type
     *
     * @param request
     * @param response
     * @param filenamePrefix 
     * @throws UnsupportedEncodingException 
     */
    protected void setPdfAttaType(HttpServletRequest request, HttpServletResponse response, String filenamePrefix)
            throws UnsupportedEncodingException {
        extPtBaseController.setPdfAttaType(request, response, filenamePrefix);
    }

    /**
     * Set ZIP attachment type
     * 
     * @param request
     * @param response
     * @param filenamePrefix 
     * @throws UnsupportedEncodingException 
     */
    protected void setZipAttaType(HttpServletRequest request, HttpServletResponse response, String filenamePrefix)
            throws UnsupportedEncodingException {
        extPtBaseController.setZipAttaType(request, response, filenamePrefix);
    }

    /**
     * Set MS PPT attachment type
     * 
     * @param request
     * @param response
     * @param filenamePrefix
     * @throws UnsupportedEncodingException 
     */
    protected void setMsPptAttaType(HttpServletRequest request, HttpServletResponse response, String filenamePrefix)
            throws UnsupportedEncodingException {
        extPtBaseController.setMsPptAttaType(request, response, filenamePrefix);
    }

    /**
     * Set response attachment type
     * 
     * @param request
     * @param response
     * @param mimeType
     * @param filename
     * @throws UnsupportedEncodingException 
     */
    protected void setResponseAttaType(HttpServletRequest request, HttpServletResponse response, String mimeType, String filename)
            throws UnsupportedEncodingException {
        extPtBaseController.setResponseAttaType(request, response, mimeType, filename);
    }

    /**
     * Base64 to string
     * 
     * @param request
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String base64ToStr(HttpServletRequest request, String str) throws UnsupportedEncodingException {
        return extPtBaseController.base64ToStr(request, str);
    }

    /**
     * String to base64
     * 
     * @param request
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String strToBase64(HttpServletRequest request, String str) throws UnsupportedEncodingException {
        return extPtBaseController.strToBase64(request, str);
    }

}
