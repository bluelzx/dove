package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.EventTypeDict;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 异步任务完成事件
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ] 
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class BatJobRsEventMsgReq extends EventMsgReq<BatJobRsEventMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 批量任务对象
     */
    @JsonProperty("BatchJob")
    @XmlElement(name = "BatchJob")
    private BatchJobMsg batchJobMsg;

    public BatJobRsEventMsgReq() {
        super();
        super.setMsgType(MsgTypeDict.EVENT);
        super.setEvent(EventTypeDict.BATCH_JOB_RS);
    }

    public BatJobRsEventMsgReq(BatchJobMsg batchJobMsg) {
        this();
        this.batchJobMsg = batchJobMsg;
    }

    public static BatJobRsEventMsgReq toBean(String xml) throws JAXBException {
        return new BatJobRsEventMsgReq().toBean(xml, BatJobRsEventMsgReq.class);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BatchJobMsg extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 异步任务id，最大长度为64字符
         */
        @JsonProperty("JobId")
        @XmlElement(name = "JobId")
        private String jobId;

        /**
         * 操作类型，字符串，目前分别有：
         * 
         * 1. sync_user(增量更新成员)
         * 2. replace_user(全量覆盖成员)
         * 3. invite_user(邀请成员关注)
         * 4. replace_party(全量覆盖部门)
         */
        @JsonProperty("JobType")
        @XmlElement(name = "JobType")
        private String jobType;

        /**
         * 返回码
         */
        @JsonProperty("ErrCode")
        @XmlElement(name = "ErrCode")
        private int errCode;

        /**
         * 对返回码的文本描述内容
         */
        @JsonProperty("ErrMsg")
        @XmlElement(name = "ErrMsg")
        private String errMsg;

        public BatchJobMsg() {
            super();
        }

        public BatchJobMsg(String jobId, String jobType, int errCode, String errMsg) {
            this();
            this.jobId = jobId;
            this.jobType = jobType;
            this.errCode = errCode;
            this.errMsg = errMsg;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public String getJobType() {
            return jobType;
        }

        public void setJobType(String jobType) {
            this.jobType = jobType;
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

}
