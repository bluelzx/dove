package com.gustz.dove.repo.account;

/**
 * TODO: 账户常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public abstract class AccConstants {

    /** 
     * Get CP account code
     * 
     * @param srcId
     * @param wecAppId
     * @return 
     */
    public static final String getCpAccountCode(final String srcId, String wecAppId) {
        //
        // 使用'srcId + "_" + wecAppId'组合 TODO
        return srcId + "_" + wecAppId;
    }

    /** 
     * Get CP source id
     * 
     * @param devAcCode
     * @return 
     */
    public static final String getCpSrcId(final String devAcCode) {
        return devAcCode.split("_")[0];
    }

    /**
     * 账户类型
     */
    public enum TypeGc {
        /** 组编码 */
        ACC_TYPE,
        /** 服务号 */
        T0,
        /** 订阅号 */
        T1,
        /** 企业号 */
        T2;

        @Override
        public String toString() {
            return ACC_TYPE.name();
        }
    }

}
