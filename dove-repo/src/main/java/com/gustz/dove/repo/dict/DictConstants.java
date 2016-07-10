package com.gustz.dove.repo.dict;

/**
 * TODO: 数据字典常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public interface DictConstants {

    /**
     * 是否类型-Y，N
     */
    enum YnTypeGc {
        /** 组编码 */
        COMM_YN_TYPE,
        /** Y-是 */
        Y,
        /** N-否 */
        N;

        @Override
        public String toString() {
            return COMM_YN_TYPE.name();
        }
    }

    /**
     * 性别-男1，女0
     */
    enum SexGc {
        /** 组编码 */
        COMM_SEX,
        /** S0-女 */
        S0,
        /** S1-男 */
        S1;

        @Override
        public String toString() {
            return COMM_SEX.name();
        }
    }

    /**
     * 运行状态-S0启用，S1停用
     */
    enum RunStateGc {
        /** 组编码 */
        COMM_RUN_STATE,
        /** S0-启用 */
        S0,
        /** S1-停用 */
        S1;

        @Override
        public String toString() {
            return COMM_RUN_STATE.name();
        }
    }

    /**
     * 邮件任务状态
     */
    enum MailTaskStateGc {
        /** 组编码 */
        COMM_MAIL_TASK_STATE,
        /** S0-已发送 */
        S0,
        /** S1-未发送 */
        S1;

        @Override
        public String toString() {
            return COMM_MAIL_TASK_STATE.name();
        }
    }

    /**
     * 文件存储路径
     */
    enum FileStorePathGc {
        /** 组编码 */
        COMM_FILE_STORE_PATH,
        /** SP0-上传的附件 */
        SP0,
        /** SP1-业务统计报表 */
        SP1;

        @Override
        public String toString() {
            return COMM_FILE_STORE_PATH.name();
        }
    }

    /**
     * 合法的文件类型
     */
    enum AuthContentTypeGc {
        /** 组编码 */
        COMM_AUTH_CONTENT_TYPE,
        /** xls-application/vnd.ms-excel */
        xls,
        /** doc-application/msword */
        doc,
        /** gif-image/gif */
        gif,
        /** jpg-image/jpeg */
        jpg,
        /** pdf-application/pdf */
        pdf,
        /** png-image/png */
        png,
        /** ppt-application/vnd.ms-powerpoint */
        ppt,
        /** rar-application/rar */
        rar,
        /** swf-application/x-shockwave-flash */
        swf,
        /** xml-application/xml */
        xml,
        /** zip-application/zip */
        zip;

        @Override
        public String toString() {
            return COMM_AUTH_CONTENT_TYPE.name();
        }
    }

}
