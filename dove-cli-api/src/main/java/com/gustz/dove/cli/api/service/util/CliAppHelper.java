package com.gustz.dove.cli.api.service.util;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.gustz.dove.cli.api.service.dict.EventTypeDict;
import com.gustz.dove.cli.api.message.req.CommEventMsgReq;
import com.gustz.dove.cli.api.service.BaseWebsUrl;
import com.gustz.dove.cli.api.service.MessageHolder;

/**
 * TODO: Client helper
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
public class CliAppHelper {

    private static Set<String> ignoreUriSet = new HashSet<String>();

    // algorithm
    private static final String AES_ALGO_KEY = "AES";

    // MD5
    private static final String MD5_ALGO_KEY = "MD5";

    // cipher algorithm
    private static final String CIPHER_ALGO = "AES/ECB/PKCS5Padding";

    public static final Charset CHARSET = Charset.forName("utf-8");

    public static final String TODAY_PATT = "yyyyMMdd010101";

    private String msgReq;

    /**
     * Get local IP address
     * 
     * @return 127.0.0.1,192.128.128.1,192.168.1.1
     * @throws SocketException
     */
    public static String[] getLocalIpAddrs() throws SocketException {
        List<String> _tmpList = new ArrayList<String>();
        Enumeration<NetworkInterface> allNetInters = NetworkInterface.getNetworkInterfaces();
        InetAddress ipAddr = null;
        NetworkInterface netInter = null;
        Enumeration<InetAddress> addrEnum = null;
        while (allNetInters.hasMoreElements()) {
            netInter = (NetworkInterface) allNetInters.nextElement();
            addrEnum = netInter.getInetAddresses();
            while (addrEnum.hasMoreElements()) {
                ipAddr = (InetAddress) addrEnum.nextElement();
                if (ipAddr != null && (ipAddr instanceof Inet4Address || ipAddr instanceof Inet6Address)) {
                    _tmpList.add(ipAddr.getHostAddress().trim());
                }
            }
        }
        if (_tmpList != null && !_tmpList.isEmpty()) {
            String ips = _tmpList.toString();
            ips = ips.replace("[", "").trim().replace("]", "").trim();
            ips = ips.replace(" ", "");
            return ips.split(",");
        }
        return null;
    }

    public static void setIgnoreUriSet(String ignoreUri) {
        if (ignoreUri != null && !ignoreUri.isEmpty()) {
            for (String uri : ignoreUri.split(",")) {
                if (uri != null && !uri.isEmpty()) {
                    ignoreUriSet.add(uri);
                }
            }
        }
    }

    public static boolean isIgnoreUri(String uri) {
        for (String str : ignoreUriSet) {
            if (uri == null || uri.isEmpty()) {
                continue;
            }
            // 匹配URI后缀 1.
            if (uri.endsWith(str)) {
                return true;
            }
            // 匹配URI前缀 2.
            if (uri.startsWith(str)) {
                return true;
            }
            // 全部匹配URI 3.
            if (str.equals(uri)) {
                return true;
            }
        }
        return false;
    }

    public static void clsIgnoreUriSet() {
        if (ignoreUriSet != null) {
            ignoreUriSet.clear();
        }
    }

    private static String decrypt(final String seed, String data) throws Exception {
        // get keyt
        MessageDigest _md5 = MessageDigest.getInstance(MD5_ALGO_KEY);
        _md5.update((seed).getBytes(CHARSET));
        byte[] keyt = _md5.digest();
        //
        Cipher cipher = Cipher.getInstance(CIPHER_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyt, AES_ALGO_KEY));
        // do decrypt
        byte[] _retVal = cipher.doFinal(DatatypeConverter.parseBase64Binary(data));
        return new String(_retVal, CHARSET);
    }

    /** 
     * Decrypt message
     * 
     * @param data
     * @return decrypt data
     * @throws Exception 
     */
    public static String decryptMsg(String data) throws Exception {
        //
        return decrypt(("$" + new SimpleDateFormat(TODAY_PATT).format(new Date()) + "￥"), data);
    }

    /**
     * Is development mode
     * 
     * @param remoteAddr
     * @return
     */
    public static boolean isDevMode(final String remoteAddr) {
        return ("127.0.0.1".equals(remoteAddr) || "0:0:0:0:0:0:0:1".equals(remoteAddr));
    }

    /** 
     * Decode URL
     * 
     * @param url
     * @return decode URL
     * @throws UnsupportedEncodingException 
     */
    public static String decodeUrl(final String url) throws UnsupportedEncodingException {
        //
        return URLDecoder.decode(url, CHARSET.name());
    }

    /** 
     * Get CP source id
     * 
     * @param devAcCode
     * @return 
     */
    public static final String getCpSrcId(final String devAcCode) {
        return devAcCode.split("_")[0];
    }

    /**
     * Get return message 
     * 
     * @param data
     * @param oauthCbUrl
     * @return
     * @throws Exception
     */
    public String getReturnMsg(final String data, OauthCbUrl oauthCbUrl) throws Exception {
        setMessageReq(data, oauthCbUrl, new MessageHolder() {
            @Override
            public void setMessage(final String msg) {
                setMsgReq(msg);
            }
        });
        // 获取用户帐号
        return CommEventMsgReq.toBean(EventTypeDict.ENTER_AGENT, msgReq).getFromUserName();
    }

    /**
     * Get message request 
     * 
     * @param data
     * @param oauthCbUrl
     * @param msgHolder
     * @return
     * @throws Exception
     */
    public void setMessageReq(final String data, OauthCbUrl oauthCbUrl, MessageHolder msgHolder) throws Exception {
        // 接收报文和解密
        final String[] _reqDatas = CliAppHelper.decryptMsg(URLDecoder.decode(data, CHARSET.name())).split(
                BaseWebsUrl.OAUTH_CBURL_KEY_EXT);
        // 获取用户推送的消息 1.
        msgHolder.setMessage(_reqDatas[0]);
        // 获取OAuth授权回调的URL 2.
        oauthCbUrl.setOauthCbUrl(_reqDatas[1]);
    }

    public interface OauthCbUrl {
        void setOauthCbUrl(String url);
    }

    public String getMsgReq() {
        return msgReq;
    }

    public void setMsgReq(String msgReq) {
        this.msgReq = msgReq;
    }

}
