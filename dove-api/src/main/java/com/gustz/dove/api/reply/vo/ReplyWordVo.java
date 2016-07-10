package com.gustz.dove.api.reply.vo;

import java.util.Date;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * 
 * TODO: 回复语VO
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
public class ReplyWordVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 回复语CODE
    private String wordCode;

    // 回复语名称
    private String wordName;

    // 回复语类型
    private String wordType;

    // 创建时间
    private Date createTime;

    // 状态
    private String status;
    private String[] statusIn;

    // 回复语内容
    private String content;

    // 账户CODE-外键多对一
    private String accountCode;

    @Override
    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWordCode() {
        return wordCode;
    }

    public void setWordCode(String wordCode) {
        this.wordCode = wordCode;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String[] getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(String[] statusIn) {
        this.statusIn = statusIn;
    }

}