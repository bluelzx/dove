package com.gustz.dove.cli.sdk.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.sinovatech.rd.wcsb.cli.api.service.util.CliAppHelper;
import com.gustz.dove.cli.sdk.base.service.UserOauthService;
import com.gustz.dove.cli.sdk.base.util.CpUserHelper;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.vo.CpUser;
import com.sinovatech.rd.wcsb.cpcli.api.security.rsp.UserOAuthRsp;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.OauthCpService;

/**
 * TODO: 微信服务平台授权servlet（企业号）
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
public class WsCliCpOauthSlet extends GenericServlet {

    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 客户端应用编码
     */
    public static String cliAppCode;

    /**
     * 客户端应用密码
     */
    public static String cliAppPwd;

    /**
     * 开发者账号
     */
    public static String devAcCode;

    /**
     * 客户端IP地址（多个使用逗号分隔）
     */
    public static String[] cliIpAddrs;

    private HttpSession session;

    @Override
    public void init() throws ServletException {
        final InputStream ins = WsCliCpOauthSlet.class.getClassLoader().getResourceAsStream("conf/wcsb-cp-cli-api.properties");
        if (ins == null) {
            throw new Error("Not found file 'conf/wcsb-cp-cli-api.properties' ");
        }
        try {
            Properties prop = new Properties();
            prop.load(ins);
            //
            cliAppCode = prop.getProperty("CLI_APP_CODE");
            if (cliAppCode == null || cliAppCode.isEmpty()) {
                throw new IllegalArgumentException("cliAppCode is null.");
            }
            cliAppPwd = prop.getProperty("CLI_APP_PWD");
            if (cliAppPwd == null || cliAppPwd.isEmpty()) {
                throw new IllegalArgumentException("cliAppPwd is null.");
            }
            devAcCode = prop.getProperty("DEV_AC_CODE");
            if (devAcCode == null || devAcCode.isEmpty()) {
                throw new IllegalArgumentException("devAcCode is null.");
            }
            // IPs
            cliIpAddrs = CliAppHelper.getLocalIpAddrs();
            //
            logger.info("初始化微信客户端参数成功cliAppCode: {},devAcCode: {} ", cliAppCode, devAcCode);
        } catch (IOException e) {
            throw new Error("Load file 'conf/wcsb-cp-cli-api.properties' is error.", e);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        session = request.getSession();
        //
        String _cbUrl = req.getParameter(BaseWebsUrl.OAUTH_CBURL_PARAM);
        String _oauthCode = req.getParameter(BaseWebsUrl.CODE_PARAM);
        String _state = req.getParameter(BaseWebsUrl.STATE_PARAM);
        //
        String _redirectUrl = new WsCliCpOauth(this.getUserOauthService(), getOauthCpService()).getCpOauthInfo(_cbUrl,
                _oauthCode, _state);
        //
        response.sendRedirect(request.getContextPath() + _redirectUrl);
    }

    private class WsCliCpOauth {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private UserOauthService userOauthService;

        private OauthCpService oauthCpService;

        /**
         * 
         * @param userOauthService
         * @param oauthCpService
         */
        public WsCliCpOauth(UserOauthService userOauthService, OauthCpService oauthCpService) {
            this.userOauthService = userOauthService;
            this.oauthCpService = oauthCpService;
        }

        /**
         * 获取企业号授权的用户URL
         * 
         * @param cbUrl
         * @param oauthCode
         * @param state
         * @return
         */
        public String getCpOauthInfo(String cbUrl, String oauthCode, String state) {
            logger.info(">>[企业号]根据oauth code获取CP成员信息，入参cbUrl: {} oauthCode: {} state: {} devAcCode: {} ", cbUrl, oauthCode,
                    state, devAcCode);
            // 获取授权失败后跳转URI
            final String _oauthFailUri = userOauthService.getOauthFailUri();
            try {
                UserOAuthRsp _rsp = this.getUserByOAuth(oauthCode);
                //
                logger.info("[企业号]根据oauth code获取CP成员信息状态: {}", _rsp.getRspMsg());
                // 校验用户登录
                final String _userId = _rsp.getBody().getUserId();
                final String _deviceId = _rsp.getBody().getDeviceId();
                CpUser _currCpUser = userOauthService.getLoginCpUser(_userId, _deviceId);
                if (_currCpUser == null || !devAcCode.equals(state)) { // 用户授权失败
                    logger.warn("[企业号]授权用户URL失败userId: {} deviceId: {} state: {}", _userId, _deviceId, state);
                    return _oauthFailUri;
                }
                //
                session.setAttribute(CpUserHelper.CURR_CP_USER_AKEY, _currCpUser);
                // 解密回调URL
                cbUrl = CliAppHelper.decodeUrl(cbUrl);
                logger.info("<<[企业号]授权用户URL成功userId: {} deviceId: {} cbUrl: {} state: {} ", _userId, _deviceId, cbUrl, state);
            } catch (Exception e) {
                logger.warn("[企业号]授权用户URL失败\n", e);
                cbUrl = _oauthFailUri;
            }
            return cbUrl;
        }

        private UserOAuthRsp getUserByOAuth(String oauthCode) {
            //
            return oauthCpService.getUserByOAuth(System.currentTimeMillis(), cliAppCode, devAcCode, oauthCode);
        }
    }

    /**
     * 获取微信用户OAUTH授权服务
     * 
     * @return
     */
    protected UserOauthService getUserOauthService() {
        return null;
    }

    /**
     * 获取微信OAUTH授权CP服务
     * 
     * @return
     */
    protected OauthCpService getOauthCpService() {
        return null;
    }

}
