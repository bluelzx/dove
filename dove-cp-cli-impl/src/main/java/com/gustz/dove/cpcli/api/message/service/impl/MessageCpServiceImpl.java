package com.gustz.dove.cpcli.api.message.service.impl;

import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
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
import com.sinovatech.rd.wcsb.cli.api.service.dict.EventTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.util.AppLogStyle;
import com.sinovatech.rd.wcsb.cli.api.service.util.ClientConstants;
import com.sinovatech.rd.wcsb.cpcli.api.customer.req.TextCustReq;
import com.sinovatech.rd.wcsb.cpcli.api.customer.req.TextCustReq.TextBodyCustReq;
import com.sinovatech.rd.wcsb.cpcli.api.customer.service.CustomerCpService;
import com.sinovatech.rd.wcsb.cpcli.api.message.service.MessageCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.OauthCpService;
import com.sinovatech.rd.wcsb.repo.account.AccConstants;

/**
 * 
 * TODO: 消息处理服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class MessageCpServiceImpl extends MessageServiceImpl implements MessageCpService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerCpService customerCpService;

    @Autowired
    private ClientAppService clientAppService;

    @Autowired
    private OauthCpService oauthCpService;

    /**
     * 转发接收企业号的消息
     * 
     * <pre>
     * 1、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
     * 2、直接回复success
     * </pre>
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param postData
     * @return
     */
    @Override
    public String dispatcherMsg(String msgSignature, String timestamp, String nonce, String postData) {
        AppLogStyle appLogs = new AppLogStyle();
        appLogs.begin(String.format("转发接收企业号的消息,msgSignature[ %1$s ],timestamp[ %2$s ],nonce[ %3$s ] ", msgSignature, timestamp,
                nonce));
        try {
            Document doc = new SAXReader().read(new StringReader(postData));
            // 企业号
            String _toUserName = doc.selectSingleNode(MsgBaseReq.TO_USER_NAME_XPATH).getText();
            if (!this.hasUserName(appLogs, "接收的企业号", _toUserName)) {
                return "";
            }
            // 企业号的应用ID
            String _agentId = doc.selectSingleNode(MsgBaseReq.AGENT_ID_XPATH).getText();
            if (StringUtils.isBlank(_agentId)) {
                appLogs.runtimeWarn(String.format("接收的企业号的应用ID[ %1$s ]不存在 ", _agentId));
                return "";
            }
            // 
            final String _key = AccConstants.getCpAccountCode(_agentId, _toUserName);
            ClientAppVo _appVo = clientAppService.getActiveCacheByAcc().get(_key);
            if (!this.hasClientApp(appLogs, "应用ID", _appVo, _agentId)) {
                return "";
            }
            // 微信解密消息
            final String atomData = this.decryptMsg(_appVo, msgSignature, timestamp, nonce, doc);
            // 发送方的账号
            String _fromUserName = CommEventMsgReq.toBean(EventTypeDict.ENTER_AGENT, atomData).getFromUserName();
            if (!this.hasUserName(appLogs, "发送方的账号", _fromUserName)) {
                return "";
            }
            //
            final String _statusKey = AccConstants.getCpAccountCode(_agentId, _appVo.getWecAppId());
            // OAuth授权URL
            String oauthCbUrl = oauthCpService.getSnsapiBaseUrl(_appVo.getCliAppCode(), _statusKey);
            String newPostData = this.localEncryptMsg(atomData, oauthCbUrl); // 本地的加密消息
            // 直接推送到接入的客户端APP
            if (!this.hasSendToCliApp(appLogs, _appVo, _fromUserName, newPostData)) {
                return "";
            }
            // 自动回复消息
            this.doAutoReplyMsg(_appVo, doc);
            //
            return ClientConstants.SUCCESS_RSP;
        } catch (Exception e) {
            appLogs.error("处理接收的企业号消息异常\n", e);
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
     * @param content 消息
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
                    // 发送客服消息
                    logger.info("CP客服自动回复消息...");
                    customerCpService.sendCustomerMsg(System.currentTimeMillis(), cliAppCode, new TextCustReq(fromUserName, body));
                }
            });
        }
    }

}
