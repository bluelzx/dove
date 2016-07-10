package com.gustz.dove.api.dict.service;

import java.util.List;

import com.gustz.dove.api.dict.vo.DictGroupVo;
import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.Order;

/**
 * 
 * TODO: 字典组别服务接口
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
public interface DictGroupService extends DataService<DictGroupVo, String> {

    /**
     * 分页查询
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    List<DictGroupVo> ecList(DictGroupVo search, int start, int limit, List<Order> orders);

    /**
     * 查询总记录数
     * 
     * @param search
     * @return
     */
    int ecCount(DictGroupVo search);

    /**
     * 是否已存在该记录-组别编码
     * 
     * @param id
     * @param groupCode
     * @return
     */
    boolean isExistGc(String id, String groupCode);

    /**
     * 是否已存在该记录-组别名称
     * 
     * @param id
     * @param groupName
     * @return
     */
    boolean isExistGname(String id, String groupName);

}
