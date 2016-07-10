package com.gustz.dove.api.dict.vo;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * TODO: Error VO
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class ErrorVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private int errCode;

    /**
     * 错误提示
     */
    private String errMsg;

    public ErrorVo() {
        super();
    }

    public ErrorVo(int errCode) {
        this();
        this.errCode = errCode;
        if (this.errCode == -1) {
            this.errMsg = "操作失败！";
        }
    }

    public ErrorVo(int errCode, String errMsg) {
        this(errCode);
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        // 成功编码 0
        return (this.getErrCode() == 0);
    }

    public boolean isFail() {
        return !this.isSuccess();
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
