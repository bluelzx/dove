package com.gustz.dove.api.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.dao.HqlDao;
import com.sinovatech.fw.dao.SqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.account.service.AccountService;
import com.sinovatech.rd.wcsb.api.account.vo.AccountVo;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.gustz.dove.api.service.util.CommConstants;
import com.sinovatech.rd.wcsb.repo.account.AccConstants;
import com.sinovatech.rd.wcsb.repo.account.dao.AccountDao;
import com.sinovatech.rd.wcsb.repo.account.po.AccountPo;
import com.sinovatech.rd.wcsb.repo.dict.DictConstants;

/**
 * 
 * TODO: 账户服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class AccountServiceImpl extends AbstractDataService<AccountVo, AccountPo, String> implements AccountService {

    @Autowired
    private HqlDao hqlDao;

    @Autowired
    private SqlDao sqlDao;

    private AccountDao dao;

    @Autowired
    private DictService dictService;

    @Autowired
    public void needDao(AccountDao dao) {
        super.setDao(dao);
        this.dao = dao;
    }

    @Override
    public String getId(AccountVo vo) {
        return vo.getId();
    }

    /**
     * 保存
     * 
     * @param vo
     * @return
     */
    @Override
    public String save(AccountVo vo) {
        AccountPo _po = this.vo2Po(vo);
        _po.setIsDelete(CommConstants.NO);
        _po.setCreateTime(new Date());
        _po.setStatus(DictConstants.RunStateGc.S1.name());
        try {
            // 账户编码（微信代理ID_wecAppId）
            if (AccConstants.TypeGc.T2.name().equals(vo.getAccountType())) { // 企业号
                _po.setAccountCode(AccConstants.getCpAccountCode(vo.getSrcId(), vo.getWecAppId()));
            } else { // 订阅号/服务号
                _po.setAccountCode(vo.getSrcId());
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.dao.save(_po);
    }

    /**
     * 更新
     * 
     * @param vo
     */
    @Override
    public void update(AccountVo vo) {
        AccountPo _newPo = this.vo2Po(vo); // 页面新值
        AccountPo _oldPo = this.dao.get(vo.getId()); // 数据库旧值
        //不能改变的值
        _newPo.setCreateTime(_oldPo.getCreateTime());
        _newPo.setIsDelete(CommConstants.NO);
        _newPo.setAccountCode(_oldPo.getAccountCode());
        _newPo.setAccountType(_oldPo.getAccountType());
        _newPo.setWecAppId(_oldPo.getWecAppId());
        _newPo.setStatus(_oldPo.getStatus());
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
            AccountPo _po = this.dao.get(_id);
            _po.setIsDelete(CommConstants.YES);
            this.dao.update(_po);
        }
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
    public List<AccountVo> ecList(AccountVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();
        //
        List<AccountPo> _poList = hqlDao.list(queryInfo.getSql(), start, limit, queryInfo.getParArr());
        //
        List<AccountVo> _list = New.list();
        if (_poList != null && _poList.size() > 0) {
            for (AccountPo po : _poList) {
                AccountVo vo = this.po2Vo(po);
                if (vo != null) {
                    this.setDict(vo);
                    //
                    _list.add(vo);
                }
            }
        }
        return _list;
    }

    /**
     * 查询总记录数
     * 
     * @param search
     * @return
     */
    @Override
    public int ecCount(AccountVo search) {
        QueryInfo queryInfo = prepareQuery(search).build();
        return hqlDao.count(queryInfo.getCountSql(), queryInfo.getParArr());
    }

    /**
     * 查询全部可用的
     * 
     * @param accountType
     * @return
     */
    @Override
    public List<AccountVo> listActive(String accountType) {
        AccountVo vo = new AccountVo();
        if (accountType != null) {
            vo.setAccountType(accountType);
        }
        vo.setStatus(DictConstants.RunStateGc.S0.name());
        //
        return this.listAll(vo);
    }

    /**
     * 查询全部
     * 
     * @param search
     * @return 
     */
    @Override
    public List<AccountVo> listAll(AccountVo search) {
        QueryInfo info = this.prepareQuery(search).build();
        //
        List<AccountPo> _list = hqlDao.listAll(info.getSql(), info.getParArr());
        return this.po2Vo(_list);
    }

    private QueryInfoBuilder prepareQuery(AccountVo search) {
        String hql = "from AccountPo Z where 1=1 ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(hql) //
                .andEq("Z.isDelete", CommConstants.NO) //
                .andEq("Z.accountCode", search.getAccountCode()) // 编码
                .andContains("Z.accountCode", search.getAccountCodeLk()) //

                .andContains("Z.accountName", search.getAccountName()) // 名称
                .andEq("Z.accountType", search.getAccountType()) // 类型
                .andEq("Z.status", search.getStatus()) // 状态
                .andEq("Z.wecAppId", search.getWecAppId()) // 唯一凭证
                .andEq("Z.wecAppSecret", search.getWecAppSecret()) // 唯一凭证密钥

                // 
                .orderDesc("Z.createTime");

        return builder;
    }

    private void setDict(AccountVo vo) {
        // 账户类型
        vo.setAccountTypeText(dictService.text(AccConstants.TypeGc.ACC_TYPE, vo.getAccountType()));
        // 状态
        vo.setStatusText(dictService.text(DictConstants.RunStateGc.COMM_RUN_STATE, vo.getStatus()));

    }

    /**
     * 是否存在唯一凭证
     * 
     * @param id
     * @param wecAppId
     * @return
     */
    @Override
    public boolean isExistAppId(String id, String wecAppId) {
        if (StringUtils.isBlank(wecAppId)) {
            return false;
        }
        return this.isExistByKw(new AccountVo(id, null, wecAppId, null));
    }

    /**
     * 是否存在凭证密钥
     * 
     * @param id
     * @param appSecret
     * @return
     */
    @Override
    public boolean isExistAppSecret(String id, String appSecret) {
        if (StringUtils.isBlank(appSecret)) {
            return false;
        }
        return this.isExistByKw(new AccountVo(id, null, null, appSecret));
    }

    /**
     * 是否已存在该记录
     * 
     * @param vo
     * @return
     */
    private boolean isExistByKw(AccountVo vo) {
        String _id = vo.getId();
        if (StringUtils.isNotBlank(_id)) { // update
            AccountPo _oldPo = this.dao.get(_id);
            String _appId = vo.getWecAppId();
            if (StringUtils.isNotBlank(_appId) && _appId.equals(_oldPo.getWecAppId())) {
                return false; // 1.没有改变
            }
            String _appSecret = vo.getWecAppSecret();
            if (StringUtils.isNotBlank(_appSecret) && _appSecret.equals(_oldPo.getWecAppSecret())) {
                return false; // 1.没有改变
            }
        }
        // 2. save/update
        vo.setId(null);
        return (this.ecCount(vo) > 0);
    }

    /**
     * 改变状态
     * 
     * @param id
     * @param status
     */
    @Override
    public void chgStatus(String id, String status) {
        AccountPo po = this.dao.get(id);
        if (DictConstants.RunStateGc.S1.name().equals(status)) { // S1停用
            po.setStatus(DictConstants.RunStateGc.S0.name());
        } else {
            po.setStatus(DictConstants.RunStateGc.S1.name());
        }
        this.dao.update(po);
    }

    /**
     * 获取有效的账户
     * 
     * @return
     */
    @Override
    public Map<String, String> getActiveAcc() {
        Map<String, String> _map = new HashMap<String, String>();
        //
        List<AccountVo> _list = listActive(null);
        if (_list != null && _list.size() > 0) {
            for (AccountVo vo : _list) {
                _map.put(vo.getAccountCode(), vo.getAccountName());
            }
        }
        return _map;
    }

}
