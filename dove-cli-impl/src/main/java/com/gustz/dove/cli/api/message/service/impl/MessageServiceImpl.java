package com.gustz.dove.cli.api.message.service.impl;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Date;

import com.gustz.dove.cli.api.security.service.wxaes.WXBizMsgCrypt;
import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import com.gustz.dove.cli.api.service.util.AESCodec;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;

import com.gustz.dove.cli.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.cli.api.message.req.MsgBaseReq;
import com.sinovatech.rd.wcsb.cli.api.message.service.MessageService;
import com.sinovatech.rd.wcsb.cli.api.security.service.EncryptService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.gustz.dove.cli.api.service.util.AppLogStyle;
import com.sinovatech.rd.wcsb.cli.api.service.util.CliAppHelper;
import com.gustz.dove.cli.api.service.util.ClientConstants;

/**
 * 
 * TODO: 消息处理服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public abstract class MessageServiceImpl extends AbstBaseService<Serializable> implements MessageService {

    @Autowired
    private EncryptService encryptService;

    /**
     * 解密消息
     * 
     * @param appVo
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param doc
     * @return
     * @throws Exception 
     */
    protected String decryptMsg(ClientAppVo appVo, String msgSignature, String timestamp, String nonce, Document doc)
            throws Exception {
        try {
            final String cliAppCode = appVo.getCliAppCode();
            final String devAcCode = appVo.getAccountCode();
            final String wecAppId = appVo.getWecAppId();
            //
            final String devToken = encryptService.getDevToken(devAcCode, wecAppId);
            final String devAesKey = encryptService.getDevAesKeyt(cliAppCode, devAcCode, wecAppId);
            //
            WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(devToken, devAesKey, appVo.getWecAppId());
            // 执行解密
            String postData = doc.selectSingleNode(MsgBaseReq.ENCRYPT_XPATH).getText();
            return msgCrypt.decryptMsg(msgSignature, timestamp, nonce, postData);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 本地的加密消息
     * 
     * @param postData
     * @param oauthCbUrl
     * @return
     * @throws Exception
     */
    protected String localEncryptMsg(String postData, String oauthCbUrl) throws Exception {
        // 执行本地加密
        final String cbUrl = BaseWebsUrl.OAUTH_CBURL_KEY_EXT + oauthCbUrl;
        //
        final String today = FastDateFormat.getInstance(CliAppHelper.TODAY_PATT).format(new Date());
        return URLEncoder.encode(AESCodec.encrypt(("$" + today + "￥"), postData + cbUrl), ClientConstants.CHARSET.name());
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        // ignored
    }

    /**
     * 校验消息发送/接收者
     * 
     * @param appLogs
     * @param prefixText
     * @param userName
     * @return
     */
    protected boolean hasUserName(AppLogStyle appLogs, String prefixText, String userName) {
        // 账号
        if (StringUtils.isBlank(userName)) {
            appLogs.runtimeWarn(String.format(prefixText + "[ %1$s ]不存在 ", userName));
            return false;
        }
        return true;
    }

    /**
     * 校验客户端APP
     * 
     * @param appLogs
     * @param prefixText
     * @param clientAppVo
     * @param toUserName
     * @return
     */
    protected boolean hasClientApp(AppLogStyle appLogs, String prefixText, ClientAppVo clientAppVo, String toUserName) {
        // 客户端APP
        if (clientAppVo == null || StringUtils.isBlank(clientAppVo.getReceiveUrl())) {
            appLogs.runtimeWarn(String.format(prefixText + "[ %1$s ]，接入的客户端不存在 ", toUserName));
            return false;
        }
        return true;
    }

    /**
     * 校验客户端APP返回信息
     * 
     * @param appLogs
     * @param clientAppVo
     * @param fromUserName
     * @param newPostData
     * @return
     */
    protected boolean hasSendToCliApp(AppLogStyle appLogs, ClientAppVo clientAppVo, String fromUserName, String newPostData) {
        // 执行发送
        String _rspVal = this.httpPost(clientAppVo.getReceiveUrl(), System.currentTimeMillis(), clientAppVo.getCliAppCode(),
                newPostData);
        if (!fromUserName.equals(_rspVal)) {
            appLogs.runtimeWarn(String.format("接收客户端返回信息[ %1$s ]与原信息[ %2$s ]不匹配", _rspVal, fromUserName));
            return false;
        }
        return true;
    }

    /**
     * 自动回复消息
     * 
     * @param clientAppVo
     * @param doc
     */
    @Deprecated
    protected void doAutoReplyMsg(ClientAppVo clientAppVo, Document doc) {
        if (ClientConstants.YES.equals(clientAppVo.getIsReplyMsg())) {
            // 事件类型
            Node _node = doc.selectSingleNode(MsgBaseReq.EVENT_TYPE_XPATH);
            if (_node != null) {
                // 消息来源
                final String _fromUserName = doc.selectSingleNode(MsgBaseReq.FROM_USER_NAME_XPATH).getText();
                // 接收者
                final String _toUserName = doc.selectSingleNode(MsgBaseReq.TO_USER_NAME_XPATH).getText();
                // TODO
                this.autoReplyMsg(_node.getText(), _fromUserName, _toUserName, clientAppVo.getCliAppCode(), "临时消息文本");
            }
        }
    }

}
