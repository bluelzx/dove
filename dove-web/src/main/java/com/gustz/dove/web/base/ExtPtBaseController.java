package com.gustz.dove.web.base;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.gustz.dove.web.util.JSONObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinovatech.fw.mvc.PtBaseController;
import com.sinovatech.rd.wcsb.api.service.util.FileUtil;

/**
 * Ext Controller基类的父级类
 */
@RequestMapping("")
@Controller
public class ExtPtBaseController extends PtBaseController {

    /** 
     * HTML cache ConcurrentHashMap
     */
    private static ConcurrentMap<String, String> htmlCacheMap = new ConcurrentHashMap<String, String>();

    @RequestMapping("/alert/err/error.html")
    public String error(HttpServletRequest request) throws Exception {
        this.setHtml(request);
        //
        return "/success";
    }

    @RequestMapping("/alert/fail/auth-fail.html")
    public String authFail(HttpServletRequest request) throws Exception {
        this.setHtml(request);
        //
        return "/success";
    }

    private void setHtml(HttpServletRequest request) throws Exception {
        final String _htmlKey = replaceDot(request, ".html");
        // HTML
        String _html = "";
        if (!htmlCacheMap.containsKey(_htmlKey) || this.isDevMode(request)) {
            _html = this.getThemes(request, _htmlKey, _html); //get themes HTML
        } else {
            _html = htmlCacheMap.get(_htmlKey); // get cache HTML
        }
        // TODO: add display=none
        _html = _html.replace("<body ", "<body style=\"display:none;\" ");
        request.setAttribute("htmls", _html);
    }

    /**
     * Forward DYNC page 
     * 
     * @param request
     * @param dataMap
     * @throws Exception
     */
    protected String forward(HttpServletRequest request, Map<String, ?> dataMap) throws Exception {
        // set HTML
        this.setHtml(request);
        // data
        if (dataMap != null && dataMap.size() > 0) {
            String _data = new JSONObjectMapper().writeValueAsString(dataMap).toString();
            _data = _data.replaceAll("\\\\t", "").replaceAll("\\\\n", "");
            _data = _data.replaceAll("\\\\r", "").replaceAll("\\\\", "");
            //
            request.setAttribute("datas", _data);
        }
        // JS URI
        request.setAttribute("js_uri", "/views" + replaceDot(request, ".js"));
        return "/success";
    }

    private static String replaceDot(HttpServletRequest request, final String tgSuffix) {
        String _servletPath = request.getServletPath();
        if (!_servletPath.contains(".")) {
            _servletPath += ".do";
        }
        return _servletPath.replaceAll("\\.\\w+", tgSuffix);
    }

    /**
     * Is development mode
     * 
     * @param request
     * @return
     */
    private boolean isDevMode(HttpServletRequest request) {
        final String _addr = request.getRemoteAddr();
        return ("127.0.0.1".equals(_addr) || "0:0:0:0:0:0:0:1".equals(_addr));
    }

    /**
    * Get themes HTML/...
    * 
    * @param request
    * @param key 
    * @param html
    * @throws Exception
    */
    private String getThemes(HttpServletRequest request, final String key, String html) throws Exception {
        // read html file
        html = FileUtil.readFileToString(new File(request.getSession().getServletContext().getRealPath("/themes/").concat(key)),
                request.getCharacterEncoding());
        html = html.replace("../../", request.getContextPath() + "/themes/"); // replace .. path
        if (html != null && !html.isEmpty()) {
            htmlCacheMap.putIfAbsent(key, html);
        }
        return html;
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
        this.setResponseAttaType(request, response, "application/vnd.ms-excel", filenamePrefix + ".xls");
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
        this.setResponseAttaType(request, response, "application/msword", filenamePrefix + ".doc");
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
        this.setResponseAttaType(request, response, "application/pdf", filenamePrefix + ".pdf");
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
        this.setResponseAttaType(request, response, "application/zip", filenamePrefix + ".zip");
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
        this.setResponseAttaType(request, response, "application/vnd.ms-powerpoint", filenamePrefix + ".ppt");
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
        String _encoding = request.getCharacterEncoding();
        response.setCharacterEncoding(_encoding);
        response.setContentType(mimeType + ";charset=" + _encoding);
        // set header
        filename = new String(filename.getBytes(this.guessIeBrowserCharset(request)), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
    }

    /**
     * Guess IE browser charset
     * 
     * @param request
     * @return
     */
    private String guessIeBrowserCharset(HttpServletRequest request) {
        String lang = request.getHeader("accept-language").toLowerCase();
        String agent = request.getHeader("user-agent").toLowerCase();
        if (lang.contains("zh-cn") && agent.contains("msie")) {
            return "GBK";
        }
        return request.getCharacterEncoding();
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
        return new String(DatatypeConverter.parseBase64Binary(str), request.getCharacterEncoding());
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
        return DatatypeConverter.printBase64Binary(str.getBytes(request.getCharacterEncoding()));
    }

}
