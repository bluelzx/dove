package com.gustz.dove.cli.api.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Error body response extends
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 10, 2015 ]
 */
public class ExtErrorBodyRsp extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    @JsonProperty("ret_code")
    private int retCode;

    /**
     * 错误提示
     */
    @JsonProperty("error_info")
    private String errorInfo;

    public ExtErrorBodyRsp() {
        super();
    }

    public ExtErrorBodyRsp(int retCode, String errorInfo) {
        this();
        this.retCode = retCode;
        this.errorInfo = errorInfo;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

}
