package com.gustz.dove.cli.api.security.service.wxaes;

/**
 * 
 * TODO: WX AesException
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class AesException extends Exception {

    private static final long serialVersionUID = 1L;

    public static final int OK = 0;
    public static final int VALIDATE_SIGNATURE_ERROR = -40001;
    public static final int PARSE_XML_ERROR = -40002;
    public static final int COMPUTE_SIGNATURE_ERROR = -40003;
    public static final int ILLEGAL_AES_KEY = -40004;
    public static final int VALIDATE_APPID_ERROR = -40005;
    public static final int ENCRYPT_AES_ERROR = -40006;
    public static final int DECRYPT_AES_ERROR = -40007;
    public static final int ILLEGAL_BUFFER = -40008;
    //public final static int EncodeBase64Error = -40009;
    //public final static int DecodeBase64Error = -40010;
    //public final static int GenReturnXmlError = -40011;

    private int code;

    private static String getMessage(int code) {
        switch (code) {
        case VALIDATE_SIGNATURE_ERROR:
            return "校验签名失败";
        case PARSE_XML_ERROR:
            return "解析xml失败";
        case COMPUTE_SIGNATURE_ERROR:
            return "计算签名失败";
        case ILLEGAL_AES_KEY:
            return "不合法的AESKey";
        case VALIDATE_APPID_ERROR:
            return "校验AppID失败";
        case ENCRYPT_AES_ERROR:
            return "AES加密失败";
        case DECRYPT_AES_ERROR:
            return "AES解密失败";
        case ILLEGAL_BUFFER:
            return "公众平台发送的xml不合法";
            //		case EncodeBase64Error:
            //			return "Base64编码失败";
            //		case DecodeBase64Error:
            //			return "Base64解码失败";
            //		case GenReturnXmlError:
            //			return "公众帐号生成回包xml失败";
        case OK:
            return "处理成功";
        default:
            return null; // cannot be
        }
    }

    public int getCode() {
        return code;
    }

    public AesException(int code) {
        super(getMessage(code));
        this.code = code;
    }

}
