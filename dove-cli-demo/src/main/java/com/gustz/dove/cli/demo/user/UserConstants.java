package com.sinovatech.rd.wcsb.cli.demo.user;

/**
 * TODO: 账户常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public interface UserConstants {

    /**
     * 状态
     */
    enum StatusGc {
        /** 组编码 */
        USER_STATUS,
        /** 已启用 */
        S0 {
            @Override
            public String toString() {
                return "已启用";
            }
        },
        /** 未启用 */
        S1 {
            @Override
            public String toString() {
                return "未启用";
            }
        },
        /** 已冻结 */
        S2 {
            @Override
            public String toString() {
                return "已冻结";
            }
        };

        @Override
        public String toString() {
            return USER_STATUS.name();
        }
    }

    /**
     * 角色类型
     */
    enum RoleTypeGc {
        /** 组编码 */
        USER_ROLE_TYPE,
        /** 系统管理员 */
        T0 {
            @Override
            public String toString() {
                return "系统管理员";
            }
        },
        /** 销售人员 */
        T1 {
            @Override
            public String toString() {
                return "销售人员";
            }
        },
        /** 备用 */
        T2 {
            @Override
            public String toString() {
                return "备用";
            }
        };

        @Override
        public String toString() {
            return USER_ROLE_TYPE.name();
        }
    }

}
