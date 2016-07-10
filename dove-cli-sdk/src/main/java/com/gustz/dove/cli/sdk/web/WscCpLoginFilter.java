package com.gustz.dove.cli.sdk.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gustz.dove.cli.sdk.base.util.CpUserHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.sinovatech.rd.wcsb.cli.api.service.util.CliAppHelper;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.vo.CpUser;

/**
 * TODO: 微信用户登录的过滤器（企业号）
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 18, 2015 ]
 */
public class WscCpLoginFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(WscCpLoginFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        CliAppHelper.setIgnoreUriSet(config.getInitParameter("ignoreUris"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) req);
        HttpServletResponse response = ((HttpServletResponse) rsp);
        try {
            String servletPath = request.getServletPath();
            if (!CliAppHelper.isIgnoreUri(servletPath)) { // 需要校验的URL 1.
                String _bfRediUrl = this.beforeOauth();
                if (_bfRediUrl != null && !_bfRediUrl.isEmpty()) {
                    // OAUTH之前的跳转URL
                    response.sendRedirect(request.getContextPath() + _bfRediUrl);
                    return;
                }
                if (CliAppHelper.isDevMode(request.getRemoteAddr())) { // 开发模式 2.
                    String _userId = request.getParameter("userId");
                    CpUserHelper.setDev(_userId);
                    logger.info("当前为开发模式servletPath: {} userId: {} ", servletPath, _userId);
                } else {
                    // 当前登录用户编码 3.
                    CpUser _currUser = (CpUser) request.getSession().getAttribute(CpUserHelper.CURR_CP_USER_AKEY);
                    if (_currUser == null) {
                        String _cbUrl = request.getRequestURL().toString();
                        String _param = request.getQueryString();
                        //
                        logger.info(">>该登录用户尚未授权");
                        final String _redirectUrl = this.getWcOauthCbUrl(_cbUrl, _param);
                        if (_redirectUrl == null || _redirectUrl.isEmpty()) {
                            // 跳转到尚未授权页405
                            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        } else {
                            // 跳转到微信授权URL
                            response.sendRedirect(_redirectUrl);
                        }
                        return;
                    } else {
                        CpUserHelper.set(_currUser);
                    }
                }
            }
            chain.doFilter(req, rsp);
        } catch (Exception e) {
            logger.error("", e);
            throw new IllegalStateException(e);
        } finally {
            CpUserHelper.clear();
        }
    }

    @Override
    public void destroy() {
        CliAppHelper.clsIgnoreUriSet();
        CpUserHelper.clear();
    }

    /**
     * 获取微信授权的回调URL
     * 
     * @param cbUrl
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String getWcOauthCbUrl(String cbUrl, final String param) throws UnsupportedEncodingException {
        if (param != null && !param.isEmpty()) {
            cbUrl += "?" + param;
        }
        cbUrl = BaseWebsUrl.OAUTH_CBURL_KEY_EXT2 + URLEncoder.encode(cbUrl, CliAppHelper.CHARSET.name());
        //
        logger.info(">>begin-跳转到微信授权cbUrl: {}", cbUrl);
        final String redirectUrl = WsCliReceiveSlet.wcOauthCbUrl.replace(BaseWebsUrl.OAUTH_CBURL_PATT, cbUrl);
        //
        logger.info("<<end-跳转到微信授权URL: {}", redirectUrl);
        return redirectUrl;
    }

    /**
     * OAUTH授权之前
     * 
     * @return redirectUrl
     */
    protected String beforeOauth() {
        return null;
    }

}
