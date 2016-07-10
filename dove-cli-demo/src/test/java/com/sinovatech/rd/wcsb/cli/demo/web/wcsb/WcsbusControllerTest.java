package com.sinovatech.rd.wcsb.cli.demo.web.wcsb;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinovatech.rd.wcsb.cli.api.message.req.CommEventMsgReq;
import com.sinovatech.rd.wcsb.cli.api.message.rsp.VideoMsgRsp;
import com.sinovatech.rd.wcsb.cli.api.message.vo.Video;
import com.sinovatech.rd.wcsb.cli.api.service.dict.EventTypeDict;

/**
 * 
 * TODO: 处理微信服务平台请求C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/wcsbus/*")
public class WcsbusControllerTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 开发者微信号ToUserName XPath
     */
    @SuppressWarnings("unused")
    private static final String TO_USER_NAME_XPATH = "/xml/ToUserName";

    public static void main(String[] args) throws Exception {
        String text = "<xml>";
        text += "<ToUserName><![CDATA[gh_b931f645aae1]]></ToUserName>";
        text += "<FromUserName><![CDATA[o-bc2v98_6iKrDS2ELiD04yd8B5k]]></FromUserName>";
        text += "<CreateTime>1439809688</CreateTime>";
        text += "<MsgType><![CDATA[event]]></MsgType>";
        text += "<Event><![CDATA[enter_agent]]></Event>";
        text += "<EventKey><![CDATA[http://www.baidu.com]]></EventKey>";
        text += "</xml>";
        //
        CommEventMsgReq req = CommEventMsgReq.toBean(EventTypeDict.ENTER_AGENT, text);
        System.out.println("bean=" + req);
        // 
        System.out.println("");
        VideoMsgRsp rsp = new VideoMsgRsp();
        rsp.setCreateTime(System.currentTimeMillis());
        rsp.setFromUserName("fromUserName");
        rsp.setFuncFlag(0);
        rsp.setToUserName("toUserName");
        rsp.setVideo(new Video("title", "描述", "mediaId"));
        //
        //String xml = VideoMsgRsp.toXml(rsp);
        //System.out.println("xml=:" + xml);
        System.out.println("");
    }

    /**
     * 接收消息
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "receiveMsg", method = RequestMethod.POST)
    public void receiveMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream ins = null;
        try {
            ins = request.getInputStream();
            // Document doc = new SAXReader().read(ins);
            // 消息事件
            //EventMsgReq req = MsgTypeDict.event.getMsgReq(doc.asXML());
            // 返回值
            // String _frUserName = doc.selectSingleNode(TO_USER_NAME_XPATH).getText();
            // response.getWriter().write(_frUserName);
        } catch (IOException e) {
            logger.error("接收消息异常 \n {}", e);
        } finally {
            if (ins != null) {
                ins.close();
            }
        }
        return;
    }

}
