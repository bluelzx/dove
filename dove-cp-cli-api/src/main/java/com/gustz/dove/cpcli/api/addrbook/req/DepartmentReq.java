package com.gustz.dove.cpcli.api.addrbook.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cpcli.api.addrbook.req.DepartmentReq.UserDeptBodyReq;

/**
 * 
 * TODO: 用户部门请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class DepartmentReq extends AbstBaseReq<UserDeptBodyReq> {

    private static final long serialVersionUID = 1L;

    public DepartmentReq(String devAcCode, UserDeptBodyReq body) {
        super(devAcCode, body);
    }

    public static class UserDeptBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 部门id，整型。指定时必须大于1，不指定时则自动生成 
         */
        @JsonProperty("id")
        private Integer id;

        /**
         * 部门名称。长度限制为1~64个字节，字符不能包括\:*?"<>｜
         */
        @JsonProperty("name")
        private String name;

        /**
         * 父亲部门id。根部门id为1
         */
        @JsonProperty("parentid")
        private int parentId;

        /**
         * 在父部门中的次序值。order值小的排序靠前。
         */
        @JsonProperty("order")
        private int order;

        public UserDeptBodyReq() {
            super();
        }

        /**
         * 
         * @param id
         * @param name
         * @param parentId
         * @param order
         */
        public UserDeptBodyReq(Integer id, String name, int parentId, int order) {
            super();
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.order = order;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

    }

}
