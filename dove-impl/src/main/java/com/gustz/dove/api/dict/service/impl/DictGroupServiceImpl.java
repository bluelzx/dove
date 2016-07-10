package com.gustz.dove.api.dict.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.dao.HqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.sinovatech.rd.wcsb.api.dict.service.DictGroupService;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo;
import com.sinovatech.rd.wcsb.repo.dict.dao.DictGroupDao;
import com.sinovatech.rd.wcsb.repo.dict.po.DictGroupPo;

/**
 * 
 * TODO: 字典组别服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class DictGroupServiceImpl extends AbstractDataService<DictGroupVo, DictGroupPo, String> implements DictGroupService {

    @Autowired
    private HqlDao hqlDao;

    private DictGroupDao dao;

    @Autowired
    private DictService dictService;

    @Autowired
    public void needDao(DictGroupDao dao) {
        super.setDao(dao);
        this.dao = dao;
    }

    @Override
    public String getId(DictGroupVo vo) {
        return vo.getId();
    }

    /**
     * 是否已存在该记录-组别编码
     * 
     * @param id
     * @param groupCode
     * @return
     */
    @Override
    public boolean isExistGc(String id, String groupCode) {
        if (StringUtils.isBlank(groupCode)) {
            return false;
        }
        return this.isExistByKw(new DictGroupVo(id, groupCode));
    }

    /**
     * 是否已存在该记录-组别名称
     * 
     * @param id
     * @param groupName
     * @return
     */
    @Override
    public boolean isExistGname(String id, String groupName) {
        if (StringUtils.isBlank(groupName)) {
            return false;
        }
        return this.isExistByKw(new DictGroupVo(id, null, groupName));
    }

    /**
     * 是否已存在该记录
     * 
     * @param vo
     * @return
     */
    private boolean isExistByKw(DictGroupVo vo) {
        String _id = vo.getId();
        if (StringUtils.isNotBlank(_id)) { // update
            DictGroupPo _oldPo = this.dao.get(_id);
            String _gcode = vo.getGroupCode();
            if (StringUtils.isNotBlank(_gcode) && _gcode.equals(_oldPo.getGroupCode())) {
                return false; // 1.没有改变组编码
            }
            String _gname = vo.getGroupName();
            if (StringUtils.isNotBlank(_gname) && _gname.equals(_oldPo.getGroupName())) {
                return false; // 1.没有改变组名称
            }
        }
        // 2. save/update
        vo.setId(null);
        return (this.ecCount(vo) > 0);
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
    public List<DictGroupVo> ecList(DictGroupVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();

        List<DictGroupPo> _list = hqlDao.list(queryInfo.getSql(), start, limit, queryInfo.getParArr());
        return this.po2Vo(_list);
    }

    /**
     * 查询总记录数
     * 
     * @param search
     * @return
     */
    @Override
    public int ecCount(DictGroupVo search) {
        QueryInfo queryInfo = prepareQuery(search).build();
        return hqlDao.count(queryInfo.getCountSql(), queryInfo.getParArr());
    }

    private QueryInfoBuilder prepareQuery(DictGroupVo search) {
        String hql = "from DictGroupPo Z where 1=1 ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(hql) //
                .andEq("Z.groupCode", search.getGroupCode()) //
                .andContains("Z.groupCode", search.getGroupCodeLk()) //
                //
                .andContains("Z.groupName", search.getGroupName()) //
                .andEq("Z.id", search.getId())
                //
                .orderDesc("Z.createTime");

        return builder;
    }

    /**
     * 批量删除字典组别
     * 
     * @param ids
     */
    @Override
    public void batchDelete(Iterable<String> ids) {
        DictGroupPo _po = null;
        for (String _id : ids) {
            _po = this.dao.get(_id);
            if (this.dictService.isExistFkDict(_po.getGroupCode())) {
                throw new IllegalStateException("Exist foreign key,delete object is fail!");
            }
            this.dao.delete(_id);
        }
    }

    /**
     * 保存字典组别
     * 
     * @param vo
     * @return
     */
    @Override
    public String save(DictGroupVo vo) {
        DictGroupPo _po = this.vo2Po(vo);
        _po.setCreateTime(new Date());
        return this.dao.save(_po);
    }

    /**
     * 更新字典组别
     * 
     * @param vo
     */
    @Override
    public void update(DictGroupVo vo) {
        DictGroupPo _newPo = this.vo2Po(vo); // 页面新值
        DictGroupPo _oldPo = this.dao.get(vo.getId()); // 数据库旧值
        //不能改变的值
        _newPo.setCreateTime(_oldPo.getCreateTime());
        this.dao.update(_newPo);
    }

    /**
     * 按ID获取字典组别
     * 
     * @param id
     * @return 
     */
    @Override
    public DictGroupVo get(String id) {
        DictGroupVo _vo = this.po2Vo(this.dao.get(id));
        return _vo;
    }

}
