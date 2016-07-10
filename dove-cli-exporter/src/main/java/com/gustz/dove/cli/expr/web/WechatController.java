package com.gustz.dove.cli.expr.web;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinovatech.fw.mvc.PtBaseController;
import com.sinovatech.rd.wcsb.cli.api.security.service.SignService;
import com.sinovatech.rd.wcsb.cpcli.api.message.service.MessageCpService;
import com.sinovatech.rd.wcsb.mpcli.api.message.service.MessageMpService;

/**
 * 
 * TODO: 处理微信请求C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/wechat/*")
public class WechatController extends PtBaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SignService signService;

    @Autowired
    private MessageMpService messageMpService;

    @Autowired
    private MessageCpService messageCpService;

    // 时间戳
    private String timestamp;

    // 随机数
    private String nonce;

    /**
     * 转发订阅、服务号请求
     * 
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/mp/dispatcher")
    public void mpDispatcher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 填充请求的属性值 1.
        timestamp = request.getParameter("timestamp"); // 时间戳
        nonce = request.getParameter("nonce"); // 随机数
        // 2.
        String method = request.getMethod();
        if ("POST".equalsIgnoreCase(method)) { // POST request
            this.doMpPost(request, response, request.getParameter("encrypt_type"));
        } else if ("GET".equalsIgnoreCase(method)) { // GET request
            this.doMpGet(request, response);
        } else {
            logger.warn("接收微信订阅、服务号的请求不是GET、POST方式 method: {}\n", method);
        }
    }

    /**
     * 验证订阅、服务号签名
     * 
     * <pre>
     * 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败。
     * </pre>
     * @param request
     * @param response
     */
    private void doMpGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String signature = request.getParameter("signature"); // 接入的加密签名
            // 验证签名
            if (signService.checkMpSign(signature, this.timestamp, this.nonce)) {
                // 随机字符串
                response.getWriter().write(request.getParameter("echostr"));
            }
        } catch (IOException e) {
            logger.error("验证订阅、服务号签名失败（输出随机字符串时） \n {}", e);
        }
    }

    /**
     * 接收订阅、服务号的消息
     * 
     * <pre>
     * 1、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
     * 2、直接回复success
     * </pre>
     * @param request
     * @param response
     * @param encryptType 加密类型
     * @throws IOException 
     */
    private void doMpPost(HttpServletRequest request, HttpServletResponse response, String encryptType) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("text/xml");
        ServletInputStream ins = null;
        try {
            ins = request.getInputStream();
            String postData = IOUtils.toString(ins); // 加密的消息串
            String msgSignature = request.getParameter("msg_signature"); // 消息体的签名
            //
            response.getWriter().write(
                    messageMpService.dispatcherMsg(encryptType, msgSignature, this.timestamp, this.nonce, postData));
        } catch (IOException e) {
            logger.error("接收订阅、服务号的消息异常 \n {}", e);
        } finally {
            if (ins != null) {
                ins.close();
            }
        }
    }

    // --------------------- 企业号 --------------------- begin
    /**
     * 转发企业号请求
     * 
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/cp/dispatcher")
    public void cpDispatcher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 填充请求的属性值 1.
        timestamp = request.getParameter("timestamp"); // 时间戳
        nonce = request.getParameter("nonce"); // 随机数
        String msgSignature = request.getParameter("msg_signature"); // 加密签名
        // 2.
        String method = request.getMethod();
        if ("POST".equalsIgnoreCase(method)) { // POST request
            this.doCpPost(request, response, msgSignature);
        } else if ("GET".equalsIgnoreCase(method)) { // GET request
            this.doCpGet(request, response, msgSignature);
        } else {
            logger.warn("接收微信企业号的请求不是GET、POST方式 method: {}\n", method);
        }
    }

    /**
     * 验证企业号签名
     * 
     * <pre>
     * 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败。
     * </pre>
     * @param request
     * @param response
     * @param msgSignature 接入的加密签名
     */
    private void doCpGet(HttpServletRequest request, HttpServletResponse response, String msgSignature) {
        try {
            String echoStr = request.getParameter("echostr"); // 随机字符串
            // 验证签名
            response.getWriter().write(signService.checkCpSign(msgSignature, this.timestamp, this.nonce, echoStr));
        } catch (Exception e) {
            logger.error("验证企业号签名失败（输出随机字符串时） \n {}", e);
        }
    }

    /**
     * 接收企业号的消息
     * 
     * <pre>
     * 1、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
     * 2、直接回复success
     * </pre>
     * @param request
     * @param response
     * @param msgSignature 消息的加密签名
     * @throws IOException 
     */
    private void doCpPost(HttpServletRequest request, HttpServletResponse response, String msgSignature) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("text/xml");
        ServletInputStream ins = null;
        try {
            ins = request.getInputStream();
            // 加密的消息串
            String postData = IOUtils.toString(ins);
            response.getWriter().write(messageCpService.dispatcherMsg(msgSignature, this.timestamp, this.nonce, postData));
        } catch (IOException e) {
            logger.error("接收企业号的消息异常 \n {}", e);
        } finally {
            if (ins != null) {
                ins.close();
            }
        }
    }

}
