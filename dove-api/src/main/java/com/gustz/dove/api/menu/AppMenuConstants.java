package com.gustz.dove.api.menu;

/**
 * TODO: 应用菜单常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public interface AppMenuConstants {

    /**
     * 状态
     */
    enum StatusGc {
        /** 组编码 */
        APP_MENU_STATUS,
        /** 已发布 */
        S0,
        /** 未发布 */
        S1;

        @Override
        public String toString() {
            return APP_MENU_STATUS.name();
        }
    }

}
