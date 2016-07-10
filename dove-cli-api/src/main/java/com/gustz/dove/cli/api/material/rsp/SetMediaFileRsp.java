package com.gustz.dove.cli.api.material.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstBaseRsp;
import com.gustz.dove.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cli.api.material.rsp.SetMediaFileRsp.SetMediaFileBodyRsp;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * TODO: 上传媒体类文件的响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class SetMediaFileRsp extends AbstBaseRsp<SetMediaFileBodyRsp> {

    private static final long serialVersionUID = 1L;

    public SetMediaFileRsp(long sn, String websCode) {
        super(sn, websCode, null, new SetMediaFileBodyRsp());
    }

    public static class SetMediaFileBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        // 媒体文件上传后，获取时的唯一标识 
        @JsonProperty("media_id")
        private String mediaId;

        // 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图） 
        @JsonProperty("type")
        private MsgTypeDict fileType;

        // 媒体文件上传时间戳
        @JsonProperty("created_at")
        private long createdAt;

        public SetMediaFileBodyRsp() {
            super();
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public MsgTypeDict getFileType() {
            return fileType;
        }

        public void setFileType(MsgTypeDict fileType) {
            this.fileType = fileType;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

    }
}
