package com.gustz.dove.cli.api.security.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import com.gustz.dove.cli.api.service.util.AESCodec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sinovatech.rd.wcsb.cli.api.security.service.EncryptService;
import com.gustz.dove.cli.api.service.util.ClientConstants;

/**
 * 
 * TODO: 加密服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 12, 2015 ]
 */
@Service
public class EncryptServiceImpl extends AbstBaseService<String> implements EncryptService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String TMP_KEYT = "19700101-JAVA-010101";

    /**
     * AES加密方式
     */
    public static String AES_ETYPE = "aes";

    /**
     * RAW加密方式
     */
    public static String RAW_ETYPE = "raw";

    /**
     * 获取客户端APP凭证
     * 
     * @param cliAppCode 客户端APP编码
     * @param devAcCode 开发者号
     * @param deviceCode 设备编码（手机设备）
     * @return
     * @throws Exception 
     */
    private String getCliAppSecret(final String cliAppCode, String devAcCode, String deviceCode) throws Exception {
        logger.info("获取客户端APP密钥 参数cliAppCode[{}] devAcCode[{}] deviceCode[{}].", cliAppCode, devAcCode, deviceCode);
        //
        if (StringUtils.isBlank(cliAppCode) || StringUtils.isBlank(devAcCode) || StringUtils.isBlank(deviceCode)) {
            throw new IllegalArgumentException("Args client app code/dev acount code/device id is null.");
        }
        // 转为16进制字符串 1.
        final StringBuffer sbf = new StringBuffer(Hex.encodeHexString(cliAppCode.getBytes(ClientConstants.CHARSET)));
        sbf.append(devAcCode).append(deviceCode);

        // 获取消息摘要（原始密钥data）
        final String _data = getPubDigest40(sbf.insert(1, "!@#").insert(sbf.length() - 3, ")(*").append("A").reverse().toString());
        // 新密钥 2.
        final String _seed = getSecretSeed(devAcCode, deviceCode);
        // 执行加密 4.
        return AESCodec.encrypt(_seed, _data);
    }

    /**
     * 获取开发者密钥AES
     * 
     * @param cliAppCode 客户端APP编码
     * @param devAcCode 开发者号
     * @param wecAppId 微信APP ID
     * @return 
     */
    @Override
    public String getDevAesKeyt(String cliAppCode, String devAcCode, String wecAppId) {
        logger.info("获取开发者密钥AES 参数cliAppCode[{}] []", cliAppCode);
        //
        Assert.hasText(cliAppCode, "client app code");
        Assert.hasText(devAcCode, "dev accoutcode");
        Assert.hasText(wecAppId, "wechat app id");
        try {
            // Base64转码
            final StringBuffer sbf = new StringBuffer();
            sbf.append(Base64.encodeBase64String((cliAppCode + devAcCode + wecAppId).getBytes(ClientConstants.CHARSET)));
            //
            final String _key = sbf.insert(2, "(*&").insert(sbf.length() - 2, "@#$").append("B").toString();
            // 获取消息摘要
            return getPubDigest40(_key) + "000";
        } catch (Exception e) {
            logger.error("获取开发者密钥AES异常\n", e);
        }
        return ClientConstants.FAIL_RSP;
    }

    /**
     * 获取开发者token
     * 
     * @param devAcCode 开发者号
     * @param wecAppId 微信APP ID
     * @return 
     */
    @Override
    public String getDevToken(String devAcCode, String wecAppId) {
        logger.info("获取开发者token 参数devAcCode[{}].", devAcCode);
        //
        Assert.hasText(devAcCode, "dev accoutcode");
        Assert.hasText(wecAppId, "wechat app id");
        try {
            // Base64转码
            final StringBuffer sbf = new StringBuffer();
            sbf.append(Base64.encodeBase64String((devAcCode + wecAppId).getBytes(ClientConstants.CHARSET)));
            //
            final String _key = sbf.insert(2, "@#$").insert(sbf.length() - 6, "^&*").append("C").reverse().toString();
            // 获取消息摘要
            return getPubDigest32(_key);
        } catch (Exception e) {
            logger.error("获取开发者token异常\n", e);
        }
        return ClientConstants.FAIL_RSP;
    }

    /**
     * 获取公用的消息摘要40
     * 
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String getPubDigest40(final String data) throws NoSuchAlgorithmException {
        // MD5密钥生成 1.
        MessageDigest _md5 = MessageDigest.getInstance(ClientConstants.MD5_ALGO);
        _md5.update(data.getBytes(ClientConstants.CHARSET));
        final String _data2 = Base64.encodeBase64String(_md5.digest());
        // SHA1密钥生成 2.
        MessageDigest _sha = MessageDigest.getInstance(ClientConstants.SHA_ALGO);
        _sha.update(_data2.getBytes(ClientConstants.CHARSET));
        //
        return Hex.encodeHexString(_sha.digest());
    }

    /**
     * 获取公用的消息摘要32
     * 
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String getPubDigest32(final String data) throws NoSuchAlgorithmException {
        // SHA1密钥生成 1.
        MessageDigest _sha = MessageDigest.getInstance(ClientConstants.SHA_ALGO);
        _sha.update(data.getBytes(ClientConstants.CHARSET));
        final String _data2 = Base64.encodeBase64String(_sha.digest());
        // MD5密钥生成 2.
        MessageDigest _md5 = MessageDigest.getInstance(ClientConstants.MD5_ALGO);
        _md5.update(_data2.getBytes(ClientConstants.CHARSET));
        //
        return Hex.encodeHexString(_md5.digest());
    }

    /**
     * 加密客户端APP数据
     * 
     * @param cliAppCode 客户端APP编码
     * @param userAcCode 账号/微信号
     * @param deviceCode 设备编码（手机设备）
     * @param data 明文数据
     * @return 密文数据
     */
    @Override
    public String encryptCliAppData(final String cliAppCode, String userAcCode, String deviceCode, String data) {
        try {
            // 复合密钥
            final String appSecret = this.getCliAppSecret(cliAppCode, userAcCode, deviceCode);
            // 旧密钥 1.
            final String _seed = getSecretSeed(userAcCode, deviceCode);
            // 新密钥 2.
            final String _newKeyt = AESCodec.decrypt(_seed, appSecret);
            // 执行加密
            return AESCodec.encrypt(getSecretSeed(_newKeyt, TMP_KEYT), data);
        } catch (Exception e) {
            logger.error("加密客户端APP数据异常\n", e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * 解密客户端APP数据
     * 
     * @param cliAppCode 客户端APP编码
     * @param userAcCode 账号/微信号
     * @param deviceCode 设备编码（手机设备）
     * @param encryptData 密文数据
     * @return 明文数据
     */
    @Override
    public String decryptCliAppData(final String cliAppCode, String userAcCode, String deviceCode, String encryptData) {
        try {
            // 复合密钥
            final String appSecret = this.getCliAppSecret(cliAppCode, userAcCode, deviceCode);
            // 旧密钥 1.
            final String _seed = getSecretSeed(userAcCode, deviceCode);
            // 新密钥 2.
            final String _newKeyt = AESCodec.decrypt(_seed, appSecret);
            // 执行加密
            return AESCodec.decrypt(getSecretSeed(_newKeyt, TMP_KEYT), encryptData);
        } catch (Exception e) {
            logger.error("解密客户端APP数据异常\n", e);
            throw new IllegalStateException(e);
        }
    }

    private static String getSecretSeed(String code, String code2) throws NoSuchAlgorithmException {
        return code + "-" + code2;
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        //ignored        
    }

}
