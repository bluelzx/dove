package com.gustz.dove.cli.sdk.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.gustz.dove.cli.sdk.base.service.ICliMsgReceiver;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinovatech.rd.wcsb.cli.api.service.util.CliAppHelper;
import com.sinovatech.rd.wcsb.cli.api.service.util.CliAppHelper.OauthCbUrl;

/**
 * TODO: 微信服务平台消息接收servlet
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
public class WsCliReceiveSlet extends GenericServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 微信OAUTH授权回调地址
     */
    public static String wcOauthCbUrl = "";

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        InputStream ins = null;
        try {
            ins = req.getInputStream();
            String fromUserName = new InnerMsgReceiver(this.getCliMsgReceiver()).getReturnMsg(IOUtils.toString(ins));
            //
            res.getWriter().write(fromUserName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (ins != null) {
                ins.close();
            }
        }
    }

    private class InnerMsgReceiver {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private ICliMsgReceiver cliMsgReceiver;

        public InnerMsgReceiver(ICliMsgReceiver cliMsgReceiver) {
            this.cliMsgReceiver = cliMsgReceiver;
        }

        /**
         * 获取返回的消息
         * 
         * @param msg
         * @throws Exception 
         */
        public String getReturnMsg(String msg) throws Exception {
            try {
                // 获取返回的消息1.
                final CliAppHelper helper = new CliAppHelper();
                final String fromUserName = helper.getReturnMsg(msg, new OauthCbUrl() {
                    @Override
                    public void setOauthCbUrl(final String url) {
                        if (url == null || url.isEmpty()) {
                            throw new IllegalStateException("无法接收OAuth授权的回调URL");
                        }
                        if (wcOauthCbUrl == null || wcOauthCbUrl.isEmpty()) {
                            wcOauthCbUrl = url;
                        }
                    }
                });
                // 处理业务消息2...
                if (cliMsgReceiver != null) {
                    cliMsgReceiver.doMsgReceiver(helper.getMsgReq());
                } else {
                    logger.warn("Not found 'Impl class of MsgReceiver'! ");
                }
                // 返回值3.
                logger.info("进入客户端应用的用户帐号：{} ", fromUserName);
                return fromUserName;
            } catch (Exception e) {
                logger.error("获取返回的消息异常\n", e);
                throw e;
            }
        }
    }

    /**
     * 获取客户消息接收服务
     * 
     * @return
     */
    protected ICliMsgReceiver getCliMsgReceiver() {
        return null;
    }

}
