package com.gustz.dove.api.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.dao.SqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.sinovatech.rd.wcsb.api.app.service.ClientAppService;
import com.sinovatech.rd.wcsb.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.gustz.dove.api.service.util.CommConstants;
import com.sinovatech.rd.wcsb.repo.app.AppConstants;
import com.sinovatech.rd.wcsb.repo.app.dao.ClientAppDao;
import com.sinovatech.rd.wcsb.repo.app.po.ClientAppPo;
import com.sinovatech.rd.wcsb.repo.dict.DictConstants;

/**
 * 
 * TODO: 客户端应用服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class ClientAppServiceImpl extends AbstractDataService<ClientAppVo, ClientAppPo, String> implements ClientAppService {

    @Autowired
    private SqlDao sqlDao;

    private ClientAppDao dao;

    private final Lock lock = new ReentrantLock();

    @Autowired
    private DictService dictService;

    @Autowired
    public void needDao(ClientAppDao dao) {
        super.setDao(dao);
        this.dao = dao;
    }

    @Override
    public String getId(ClientAppVo vo) {
        return vo.getId();
    }

    /**
     * 保存
     * 
     * @param vo
     * @return
     */
    @Override
    public String save(ClientAppVo vo) {
        ClientAppPo _po = this.vo2Po(vo);
        _po.setIsDelete(CommConstants.NO);
        _po.setCreateTime(new Date());
        _po.setStatus(AppConstants.StatusGc.S1.name());
        try {
            // APP编码
            _po.setCliAppCode(AppConstants.getCliAppCode(vo.getAccountCode()));
            // APP密码
            _po.setCliAppPwd(AppConstants.getCliAppPwd(vo.getAccountCode()));
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
    public void update(ClientAppVo vo) {
        ClientAppPo _newPo = this.vo2Po(vo); // 页面新值
        ClientAppPo _oldPo = this.dao.get(vo.getId()); // 数据库旧值
        //不能改变的值
        _newPo.setCreateTime(_oldPo.getCreateTime());
        _newPo.setIsDelete(CommConstants.NO);
        _newPo.setCliAppPwd(_oldPo.getCliAppPwd());
        _newPo.setAccountCode(_oldPo.getAccountCode());
        _newPo.setCliAppCode(_oldPo.getCliAppCode());
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
            ClientAppPo _po = this.dao.get(_id);
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
    public List<ClientAppVo> listAll(ClientAppVo search) {
        QueryInfo info = this.prepareQuery(search).build();
        //
        List<ClientAppVo> _list = sqlDao.listAll(info.getSql(), ClientAppVo.class, info.getParArr());
        return _list;
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
    public List<ClientAppVo> ecList(ClientAppVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();
        //
        List<ClientAppVo> _list = sqlDao.list(queryInfo.getSql(), start, limit, ClientAppVo.class, queryInfo.getParArr());
        if (_list != null && _list.size() > 0) {
            for (ClientAppVo vo : _list) {
                this.setDict(vo);
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
    public int ecCount(ClientAppVo search) {
        QueryInfo queryInfo = prepareQuery(search).build();
        //
        return sqlDao.count(queryInfo.getCountSql(), queryInfo.getParArr());
    }

    private QueryInfoBuilder prepareQuery(ClientAppVo search) {
        String hql = "select t.*,t1.ACCOUNT_NAME,t1.WEC_APP_ID,t1.WEC_APP_SECRET from WCSB_CLIENT_APP t,WCSB_ACCOUNT t1 where t.ACCOUNT_CODE=t1.ACCOUNT_CODE ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(hql) //
                .andEq("t.IS_DELETE", CommConstants.NO) //
                .andEq("t.CLI_APP_CODE", search.getCliAppCode()) // 编码
                .andContains("t.CLI_APP_CODE", search.getCliAppCodeLk()) //
                //
                .andContains("t.CLI_APP_NAME", search.getCliAppName()) // 名称
                .andIn("t.WEBS_CODES", search.getWebsCodesIn()) // 服务编码集
                .andEq("t.STATUS", search.getStatus()) // 状态
                .andIn("t.STATUS", search.getStatusIn()) // 状态多个
                .andEq("t.ACCOUNT_CODE", search.getAccountCode()) // 账号
                .andEq("t.IS_REPLY_MSG", search.getIsReplyMsg())
                //
                .andEq("t1.IS_DELETE", CommConstants.NO) //
                .andEq("t1.STATUS", DictConstants.RunStateGc.S0.name()) // 账户状态：S0已启用
                // 
                .orderDesc("t.CREATE_TIME");

        return builder;
    }

    private void setDict(ClientAppVo vo) {
        // 是否回复
        vo.setIsReplyMsgText(dictService.text(DictConstants.YnTypeGc.COMM_YN_TYPE, vo.getIsReplyMsg()));
        // 状态
        vo.setStatusText(dictService.text(AppConstants.StatusGc.APP_STATUS, vo.getStatus()));

    }

    /**
     * 改变状态
     * 
     * @param id
     * @param status
     */
    @Override
    public void chgStatus(String id, String status) {
        lock.lock();
        try {
            ClientAppPo po = this.dao.get(id);
            // S1停用 S99接入
            if (AppConstants.StatusGc.S1.name().equals(status) || AppConstants.StatusGc.S99.name().equals(status)) {
                po.setStatus(AppConstants.StatusGc.S0.name());
            } else {
                po.setStatus(AppConstants.StatusGc.S1.name());
            }
            this.dao.update(po);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 改变为开发接入状态
     * 
     * @param id 
     */
    @Override
    public void chgDevStatus(String id) {
        ClientAppPo po = this.dao.get(id);
        po.setStatus(AppConstants.StatusGc.S99.name());
        this.dao.update(po);
    }

    /**
     * 获取有效的客户端应用名称
     * 
     * @return key=CliAppCode value=cliAppName
     */
    @Override
    public Map<String, String> getActiCliAppName() {
        Map<String, String> _map = new HashMap<String, String>();
        //
        List<ClientAppVo> _appMap = this.listAll(new ClientAppVo(null, AppConstants.StatusGc.S0.name()));
        for (ClientAppVo vo : _appMap) {
            _map.put(vo.getCliAppCode(), vo.getCliAppName());
        }
        return _map;
    }

}
