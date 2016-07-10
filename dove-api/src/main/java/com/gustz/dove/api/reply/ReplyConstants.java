package com.gustz.dove.api.reply;

/**
 * TODO: 回复语常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public interface ReplyConstants {

    /**
     * 类型
     */
    enum TypeGc {
        /** 组编码 */
        REPLY_TYPE,
        /** T0-关注时回复语 */
        T0,
        /** T1- */
        T1,
        /** T2- */
        T2,
        /** T3- */
        T3;

        @Override
        public String toString() {
            return REPLY_TYPE.name();
        }
    }

}
