package com.gustz.dove.cli.api.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Error body response
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class ErrorBodyRsp extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 成功编码0
     */
    public static final String OK_CODE = "0";

    /**
     * 错误编码key-->errcode
     */
    public static final String EXT_ERR_CODE_KEY = "errcode";

    /**
     * 错误编码key-->errmsg
     */
    public static final String EXT_ERR_MSG_KEY = "errmsg";

    /**
     * 错误编码
     */
    @JsonProperty("errcode")
    private int errCode;

    /**
     * 错误提示
     */
    @JsonProperty("errmsg")
    private String errMsg;

    public ErrorBodyRsp() {
        super();
    }

    public ErrorBodyRsp(int errCode, String errMsg) {
        this();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
