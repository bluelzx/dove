package com.gustz.dove.repo.reply.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.sinovatech.fw.po.AbstractBasePo;

/**
 * 
 * TODO: 对应表WCSB_REPLY_WORD 回复语表
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
@Entity
@Table(name = "WCSB_REPLY_WORD", uniqueConstraints = @UniqueConstraint(columnNames = "WORD_CODE"))
public class ReplyWordPo extends AbstractBasePo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 回复语CODE
    private String wordCode;

    // 回复语名称
    private String wordName;

    // 回复语类型
    private String wordType;

    // 是否删除(Y：删除 N：正常)
    private String isDelete;

    // 创建时间
    private Date createTime;

    // 状态
    private String status;

    // 回复语内容
    private String content;

    // 账户CODE-外键多对一
    private String accountCode;

    @Override
    public String toString() {
        return super.toString(this);
    }

    public ReplyWordPo() {
        super();
    }

    public ReplyWordPo(String id) {
        this();
        this.setId(id);
    }

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", nullable = false, length = 32)
    @Override
    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    @Column(name = "IS_DELETE", length = 6)
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "CONTENT", length = 3000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "WORD_CODE", length = 32)
    public String getWordCode() {
        return wordCode;
    }

    public void setWordCode(String wordCode) {
        this.wordCode = wordCode;
    }

    @Column(name = "WORD_NAME", length = 60)
    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    @Column(name = "WORD_TYPE", length = 6)
    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    @Column(name = "ACCOUNT_CODE", length = 120)
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}