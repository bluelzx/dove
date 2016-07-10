package com.gustz.dove.cli.api.service.vo;

import java.io.File;
import java.util.Map;

/**
 * 
 * TODO: Upload file form
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 31, 2015 ]
 */
public class UploadFileForm extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * media file key
     */
    public static final String MEDIA_KEY = "media";

    // 文件KEY
    private String fileKey;

    // 文件内容
    private File fileValue;

    // 文本内容
    private Map<String, String> textBody;

    public UploadFileForm(String fileKey, File fileValue, Map<String, String> textBody) {
        super();
        this.fileKey = fileKey;
        this.fileValue = fileValue;
        this.textBody = textBody;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public File getFileValue() {
        return fileValue;
    }

    public void setFileValue(File fileValue) {
        this.fileValue = fileValue;
    }

    public Map<String, String> getTextBody() {
        return textBody;
    }

    public void setTextBody(Map<String, String> textBody) {
        this.textBody = textBody;
    }

}
