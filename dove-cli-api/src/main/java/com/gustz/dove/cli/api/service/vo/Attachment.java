package com.gustz.dove.cli.api.service.vo;

import java.io.BufferedInputStream;
import java.net.URI;

import com.gustz.dove.cli.api.service.dict.RspCodeDict;

/**
 * 
 * TODO: 附件
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 9, 2015 ]
 */
public class Attachment extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    // 文件名称
    private String fileName;

    // 文件全名称
    private String fullName;

    // 后缀名
    private String suffix;

    // 内容长度
    private long contentLength;

    // 上下文类型
    private String contentType;

    // 文件输入流
    private BufferedInputStream fileStream;

    // 响应消息
    private RspCodeDict rspCode;

    // URI路径
    private URI uri;

    public Attachment() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public BufferedInputStream getFileStream() {
        return fileStream;
    }

    public void setFileStream(BufferedInputStream fileStream) {
        this.fileStream = fileStream;
    }

    public RspCodeDict getRspCode() {
        return rspCode;
    }

    public void setRspCode(RspCodeDict rspCode) {
        this.rspCode = rspCode;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

}
