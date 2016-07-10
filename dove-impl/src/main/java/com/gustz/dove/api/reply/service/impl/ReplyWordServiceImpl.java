package com.gustz.dove.api.reply.service.impl;

import java.util.Date;
import java.util.List;

import com.gustz.dove.api.service.util.CommConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.dao.HqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.sinovatech.rd.wcsb.api.reply.service.ReplyWordService;
import com.sinovatech.rd.wcsb.api.reply.vo.ReplyWordVo;
import com.sinovatech.rd.wcsb.repo.dict.DictConstants;
import com.sinovatech.rd.wcsb.repo.reply.dao.ReplyWordDao;
import com.sinovatech.rd.wcsb.repo.reply.po.ReplyWordPo;

/**
 * 
 * TODO: 回复语服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class ReplyWordServiceImpl extends AbstractDataService<ReplyWordVo, ReplyWordPo, String> implements ReplyWordService {

    @Autowired
    private HqlDao hqlDao;

    private ReplyWordDao dao;

    @Autowired
    public void needDao(ReplyWordDao dao) {
        super.setDao(dao);
        this.dao = dao;
    }

    @Override
    public String getId(ReplyWordVo vo) {
        return vo.getId();
    }

    /**
     * 保存
     * 
     * @param vo
     * @return
     */
    @Override
    public String save(ReplyWordVo vo) {
        ReplyWordPo _po = this.vo2Po(vo);
        _po.setIsDelete(CommConstants.NO);
        _po.setCreateTime(new Date());
        _po.setStatus(DictConstants.RunStateGc.S1.name());
        return this.dao.save(_po);
    }

    /**
     * 更新 
     * 
     * @param vo
     */
    @Override
    public void update(ReplyWordVo vo) {
        ReplyWordPo _newPo = this.vo2Po(vo); // 页面新值
        ReplyWordPo _oldPo = this.dao.get(vo.getId()); // 数据库旧值
        //不能改变的值
        _newPo.setCreateTime(_oldPo.getCreateTime());
        _newPo.setIsDelete(CommConstants.NO);
        _newPo.setWordCode(_oldPo.getWordCode());
        this.dao.update(_newPo);
    }

    /**
     * 批量删除字典组别
     * 
     * @param ids
     */
    @Override
    public void batchDelete(Iterable<String> ids) {
        for (String _id : ids) {
            ReplyWordPo _po = this.dao.get(_id);
            _po.setIsDelete(CommConstants.YES);
            this.dao.update(_po);
        }
    }

    /**
     * 查询全部
     * 
     * @param search
     * @return 
     */
    @Override
    public List<ReplyWordVo> listAll(ReplyWordVo search) {
        return this.ecList(search, 1, CommConstants.LIMIT_MAX, null);
    }

    /**
     * 分页查询
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    @Override
    public List<ReplyWordVo> ecList(ReplyWordVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();

        List<ReplyWordPo> _list = hqlDao.list(queryInfo.getSql(), start, limit, queryInfo.getParArr());
        return this.po2Vo(_list);
    }

    /**
     * 查询总记录数
     * 
     * @param search
     * @return
     */
    @Override
    public int ecCount(ReplyWordVo search) {
        QueryInfo queryInfo = prepareQuery(search).build();

        return hqlDao.count(queryInfo.getCountSql(), queryInfo.getParArr());
    }

    private QueryInfoBuilder prepareQuery(ReplyWordVo search) {
        String hql = "from ReplyWordPo t where 1=1 ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(hql) //
                .andEq("t.isDelete", CommConstants.NO) //
                .andEq("t.wordCode", search.getWordCode()) // 编码
                .andContains("t.wordName", search.getWordName()) // 名称
                .andEq("t.wordType", search.getWordType()) // 类型
                .andEq("t.status", search.getStatus()) // 状态
                .andIn("t.status", search.getStatusIn()) // 状态多个
                // order
                .orderDesc("t.createTime");

        return builder;
    }

}
