package com.gustz.dove.repo.app;

import java.security.MessageDigest;

/**
 * TODO: 客户端应用常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public abstract class AppConstants {

    /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /** 
     * Get client app code
     * 
     * @param accountCode
     * @return secret keyt
     * @throws Exception 
     */
    public static final String getCliAppCode(final String accountCode) throws Exception {
        //
        return getSecretKeyt(("!!!" + accountCode + System.currentTimeMillis() + "###"));
    }

    /** 
     * Get client app pwd
     * 
     * @param accountCode
     * @return secret keyt
     * @throws Exception 
     */
    public static final String getCliAppPwd(final String accountCode) throws Exception {
        //
        return getSecretKeyt(("$$$" + System.currentTimeMillis() + accountCode + "&&&"));
    }

    private static final String getSecretKeyt(final String seed) throws Exception {
        MessageDigest _md5 = MessageDigest.getInstance("MD5");
        _md5.update(seed.getBytes("utf-8"));
        //
        return new String(encodeHex(_md5.digest()));
    }

    public static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    /**
     * 状态
     */
    public enum StatusGc {
        /** 组编码 */
        APP_STATUS,
        /** 已启用 */
        S0,
        /** 已停用 */
        S1,
        /** 开发接入 */
        S99;

        @Override
        public String toString() {
            return APP_STATUS.name();
        }
    }

}
