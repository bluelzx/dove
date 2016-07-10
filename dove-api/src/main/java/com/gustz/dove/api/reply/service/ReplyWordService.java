package com.gustz.dove.api.reply.service;

import java.util.List;

import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.Order;
import com.gustz.dove.api.reply.vo.ReplyWordVo;

/**
 * TODO: 回复语服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface ReplyWordService extends DataService<ReplyWordVo, String> {

    /**
     * 分页查询列表 EC
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    List<ReplyWordVo> ecList(ReplyWordVo search, int start, int limit, List<Order> orders);

    /**
     * 查询总记录数 EC
     * 
     * @param search
     * @return
     */
    int ecCount(ReplyWordVo search);

    /**
     * 查询全部
     * 
     * @param search
     * @return
     */
    List<ReplyWordVo> listAll(ReplyWordVo search);

}
