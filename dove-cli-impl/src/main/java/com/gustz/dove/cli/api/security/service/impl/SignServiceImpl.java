package com.gustz.dove.cli.api.security.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import com.gustz.dove.cli.api.security.service.wxaes.WXBizMsgCrypt;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustz.dove.cli.api.app.service.impl.ClientAppService;
import com.gustz.dove.cli.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.cli.api.security.service.EncryptService;
import com.sinovatech.rd.wcsb.cli.api.security.service.SignService;
import com.gustz.dove.cli.api.service.conf.BaseWcsbConf;
import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import com.gustz.dove.cli.api.service.util.AppLogStyle;
import com.gustz.dove.cli.api.service.util.ClientConstants;

/**
 * 
 * TODO: 验证签名服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class SignServiceImpl extends AbstBaseService<String> implements SignService {

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private BaseWcsbConf baseWcsbConf;

    @Autowired
    private ClientAppService clientAppService;

    /**
     * 验证订阅、服务号加密签名
     * 
     * <pre>
     * 1.将token、timestamp、nonce三个参数进行字典序排序。
     * 2.将三个参数字符串拼接成一个字符串进行sha1加密。
     * 3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信。
     * </pre>
     * @param signature 加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    @Override
    public boolean checkMpSign(String signature, String timestamp, String nonce) {
        boolean flag = false;
        AppLogStyle appLogs = new AppLogStyle();
        appLogs.begin(String.format("验证订阅、服务号加密签名,signature[ %1$s ],timestamp[ %2$s ],nonce[ %3$s ].", signature, timestamp,
                nonce));
        //
        long _nowTt = (new Date().getTime() / 1000);
        try {
            // 签名有效时间间隔，单位：秒。 1439783917499 1439783899
            long _ttLimit = baseWcsbConf.getTimestampLimit();
            if (Math.abs(_nowTt - Long.parseLong(timestamp)) <= _ttLimit) {
                // 将token、timestamp、nonce三个参数进行字典排序
                String[] _tmps = new String[] { this.getCurrDevToken(), timestamp, nonce };
                Arrays.sort(_tmps);
                String content = "";
                for (int i = 0, len = _tmps.length; i < len; i++) {
                    content += _tmps[i];
                }
                // 将三个参数字符串拼接成一个字符串进行sha1加密
                MessageDigest md = MessageDigest.getInstance(ClientConstants.SHA_ALGO);
                content = Hex.encodeHexString(md.digest(content.getBytes()));
                // 将sha1加密后的字符串与signature对比
                flag = (content != null && !content.isEmpty()) ? content.equals(signature) : false;
                if (flag) {
                    clientAppService.enableCliApp(); // 配置授权成功，修改APP状态为启用。
                    appLogs.runtime("验证订阅、服务号加密签名成功，启用该客户端APP。");
                }
            } else {
                appLogs.runtimeWarn(String.format("订阅、服务号重复签名signature[ %1$s ],timestamp[ %2$s ],nonce[ %3$s ]. \n", signature,
                        timestamp, nonce));
            }
        } catch (Exception e) {
            appLogs.error("验证订阅、服务号加密签名异常 \n", e);
        } finally {
            appLogs.end(String.format("验证订阅、服务号加密签名,flag=:%1$s ", flag));
        }
        return flag;
    }

    private String getCurrDevToken() throws NoSuchAlgorithmException {
        ClientAppVo vo = clientAppService.getCurrCliApp();
        //
        return encryptService.getDevToken(vo.getAccountCode(), vo.getWecAppId());
    }

    /**
     * 验证企业号加密签名
     * 
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param echoStr
     * @return
     * @throws Exception 
     */
    @Override
    public String checkCpSign(String signature, String timestamp, String nonce, String echoStr) throws Exception {
        AppLogStyle appLogs = new AppLogStyle();
        appLogs.begin(String.format("验证企业号加密签名,signature[ %1$s ],timestamp[ %2$s ],nonce[ %3$s ].", signature, timestamp, nonce));
        //
        ClientAppVo _appVo = clientAppService.getCurrCliApp();
        if (_appVo == null) {
            throw new IllegalStateException("该客户端APP不是接入状态");
        }
        final String cliAppCode = _appVo.getCliAppCode();
        final String devAcCode = _appVo.getAccountCode();
        final String wecAppId = _appVo.getWecAppId();
        //
        final String devToken = encryptService.getDevToken(devAcCode, wecAppId);
        final String devAesKey = encryptService.getDevAesKeyt(cliAppCode, devAcCode, wecAppId);
        //
        WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(devToken, devAesKey, _appVo.getWecAppId());
        // 执行校验
        String _retVal = msgCrypt.verifyUrl(signature, timestamp, nonce, echoStr);
        if (StringUtils.isNotBlank(_retVal)) {
            clientAppService.enableCliApp(); // 配置授权成功，修改APP状态为启用。
            appLogs.runtime("验证企业号加密签名成功，启用该客户端APP。");
        }
        return _retVal;
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        //ignored        
    }

}
