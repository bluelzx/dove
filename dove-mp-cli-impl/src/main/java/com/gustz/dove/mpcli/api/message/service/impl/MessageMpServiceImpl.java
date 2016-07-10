package com.gustz.dove.mpcli.api.message.service.impl;

import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.app.service.impl.ClientAppService;
import com.sinovatech.rd.wcsb.cli.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.TextCust;
import com.sinovatech.rd.wcsb.cli.api.message.req.CommEventMsgReq;
import com.sinovatech.rd.wcsb.cli.api.message.req.MsgBaseReq;
import com.sinovatech.rd.wcsb.cli.api.message.service.impl.MessageServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.security.service.impl.EncryptServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.dict.EventTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.util.AppLogStyle;
import com.sinovatech.rd.wcsb.cli.api.service.util.ClientConstants;
import com.sinovatech.rd.wcsb.mpcli.api.customer.req.TextCustReq;
import com.sinovatech.rd.wcsb.mpcli.api.customer.req.TextCustReq.TextBodyCustReq;
import com.sinovatech.rd.wcsb.mpcli.api.customer.service.CustomerMpService;
import com.sinovatech.rd.wcsb.mpcli.api.message.service.MessageMpService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.OauthMpService;

/**
 * 
 * TODO: 消息处理服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class MessageMpServiceImpl extends MessageServiceImpl implements MessageMpService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientAppService clientAppService;

    @Autowired
    private CustomerMpService customerMpService;

    @Autowired
    private OauthMpService oauthMpService;

    /**
     * 转发接收订阅、服务号的消息
     * 
     * <pre>
     * 1、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
     * 2、直接回复success
     * </pre>
     * @param encryptType
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param postData
     * @return
     */
    @Override
    public String dispatcherMsg(String encryptType, String msgSignature, String timestamp, String nonce, String postData) {
        AppLogStyle appLogs = new AppLogStyle();
        appLogs.begin(String.format("转发接收订阅、服务号的消息,msgSignature[ %1$s ],timestamp[ %2$s ],nonce[ %3$s ],encryptType[ %4$s ] ",
                msgSignature, timestamp, nonce, encryptType));
        try {
            Document doc = new SAXReader().read(new StringReader(postData));
            // 订阅、服务号
            String _toUserName = doc.selectSingleNode(MsgBaseReq.TO_USER_NAME_XPATH).getText();
            if (!this.hasUserName(appLogs, "接收的订阅、服务号", _toUserName)) {
                return "";
            }
            ClientAppVo _appVo = clientAppService.getActiveCacheByAcc().get(_toUserName);
            if (!this.hasClientApp(appLogs, "订阅、服务原始ID", _appVo, _toUserName)) {
                return "";
            }
            String newPostData = postData;
            if (EncryptServiceImpl.AES_ETYPE.equalsIgnoreCase(encryptType)) {
                // 微信解密消息
                newPostData = this.decryptMsg(_appVo, msgSignature, timestamp, nonce, doc);
            }
            // 发送方的账号
            String _fromUserName = CommEventMsgReq.toBean(EventTypeDict.ENTER_AGENT, newPostData).getFromUserName();
            if (!this.hasUserName(appLogs, "发送方的账号", _fromUserName)) {
                return "";
            }
            // OAuth授权URL
            String oauthCbUrl = oauthMpService.getSnsapiBaseUrl(_appVo.getCliAppCode(), _toUserName);
            // 本地的加密消息
            newPostData = this.localEncryptMsg(newPostData, oauthCbUrl);
            // 直接推送到接入的客户端APP
            if (!this.hasSendToCliApp(appLogs, _appVo, _fromUserName, newPostData)) {
                return "";
            }
            // 自动回复消息
            this.doAutoReplyMsg(_appVo, doc);
            //
            return ClientConstants.SUCCESS_RSP;
        } catch (Exception e) {
            appLogs.error("处理接收的订阅、服务号消息异常\n", e);
        } finally {
            appLogs.end();
        }
        return "";
    }

    /**
     * 自动回复消息（客服回复）
     * 
     * <pre>
     * 异步发送客服消息
     * </pre>
     * @param eventType 事件类型
     * @param toUserName 接收者
     * @param fromUserName 发送者
     * @param cliAppCode 客户端APP编码
     * @param content 消息内容
     */
    @Override
    public void autoReplyMsg(final String eventType, final String toUserName, final String fromUserName, final String cliAppCode,
            final String content) {
        // 不需要校验客户端应用
        this.setCheckCliApp(false);
        // 根据事件类型过滤回复语
        if (EventTypeDict.SUBSCRIBE.name().equalsIgnoreCase(eventType)) { // 关注事件
            ExecutorService exeService = Executors.newSingleThreadExecutor();
            exeService.execute(new Runnable() {
                @Override
                public void run() {
                    // 文本消息请求的报文
                    TextBodyCustReq body = new TextBodyCustReq(toUserName, new TextCust(content));
                    logger.info("MP客服自动回复消息...");
                    // 发送客服消息
                    customerMpService.sendCustomerMsg(System.currentTimeMillis(), cliAppCode, new TextCustReq(fromUserName, body));
                }
            });
        }
    }
}
