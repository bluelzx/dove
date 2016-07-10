package com.gustz.dove.api.menu.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.dao.SqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.ErrorVo;
import com.sinovatech.rd.wcsb.api.menu.AppMenuConstants;
import com.sinovatech.rd.wcsb.api.menu.service.AppMenuService;
import com.sinovatech.rd.wcsb.api.menu.vo.AppMenuVo;
import com.gustz.dove.api.service.util.CommConstants;
import com.sinovatech.rd.wcsb.repo.app.AppConstants;
import com.sinovatech.rd.wcsb.repo.dict.DictConstants;
import com.sinovatech.rd.wcsb.repo.menu.dao.AppMenuDao;
import com.sinovatech.rd.wcsb.repo.menu.po.AppMenuPo;

/**
 * 
 * TODO: 应用菜单服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class AppMenuServiceImpl extends AbstractDataService<AppMenuVo, AppMenuPo, String> implements AppMenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlDao sqlDao;

    private AppMenuDao dao;

    private final Lock lock = new ReentrantLock();

    @Autowired
    private DictService dictService;

    @Autowired
    public void needDao(AppMenuDao dao) {
        super.setDao(dao);
        this.dao = dao;
    }

    @Override
    public String getId(AppMenuVo vo) {
        return vo.getId();
    }

    /**
     * 保存
     * 
     * @param vo
     * @return
     */
    @Override
    public String save(AppMenuVo vo) {
        AppMenuPo _po = this.vo2Po(vo);
        _po.setIsDelete(CommConstants.NO);
        _po.setCreateTime(new Date());
        _po.setStatus(AppMenuConstants.StatusGc.S1.name());
        _po.setAppMenuCode(CommConstants.getCommCode());
        return this.dao.save(_po);
    }

    /**
     * 更新 
     * 
     * @param vo
     */
    @Override
    public void update(AppMenuVo vo) {
        AppMenuPo _newPo = this.vo2Po(vo); // 页面新值
        AppMenuPo _oldPo = this.dao.get(vo.getId()); // 数据库旧值
        //不能改变的值
        _newPo.setCreateTime(_oldPo.getCreateTime());
        _newPo.setIsDelete(CommConstants.NO);
        _newPo.setAppMenuCode(_oldPo.getAppMenuCode());
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
            AppMenuPo _po = this.dao.get(_id);
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
    public List<AppMenuVo> listAll(AppMenuVo search) {
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
    public List<AppMenuVo> ecList(AppMenuVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();

        List<AppMenuVo> _list = sqlDao.list(queryInfo.getSql(), start, limit, AppMenuVo.class, queryInfo.getParArr());
        if (_list != null && _list.size() > 0) {
            for (AppMenuVo vo : _list) {
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
    public int ecCount(AppMenuVo search) {
        QueryInfo queryInfo = prepareQuery(search).build();

        return sqlDao.count(queryInfo.getCountSql(), queryInfo.getParArr());
    }

    private QueryInfoBuilder prepareQuery(AppMenuVo search) {
        String sql = "select t.*,t1.CLI_APP_NAME,t1.WEBS_CODES,t1.ACCOUNT_CODE,t2.ACCOUNT_TYPE ";
        sql += " from WCSB_APP_MENU t,WCSB_CLIENT_APP t1,WCSB_ACCOUNT t2 where 1=1 ";
        sql += " and t.CLI_APP_CODE=t1.CLI_APP_CODE and t1.ACCOUNT_CODE=t2.ACCOUNT_CODE ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(sql) //
                .andEq("t.IS_DELETE", CommConstants.NO) //
                .andEq("t.ID", search.getId()) //
                .andEq("t.STATUS", search.getStatus()) // 状态
                .andEq("t.CLI_APP_CODE", search.getCliAppCode()) // 客户端应用编码
                //
                .andEq("t.APP_MENU_CODE", search.getAppMenuCode()) // 菜单编码
                .andContains("t.APP_MENU_CODE", search.getAppMenuCodeLk()) // 
                .andContains("t.APP_MENU_NAME", search.getAppMenuName()) // 菜单名称
                // 客户端应用VO
                .andEq("t1.IS_DELETE", CommConstants.NO) //
                .andEq("t1.STATUS", AppConstants.StatusGc.S0.name()) // 启用状态S0
                // 账户VO
                .andEq("t2.IS_DELETE", CommConstants.NO) //
                .andEq("t2.STATUS", DictConstants.RunStateGc.S0.name()) // 启用状态S0
                //
                .orderDesc("t.CREATE_TIME");

        return builder;
    }

    private void setDict(AppMenuVo vo) {
        // 状态
        vo.setStatusText(dictService.text(AppMenuConstants.StatusGc.APP_MENU_STATUS, vo.getStatus()));
    }

    /**
     * 是否存在菜单编码
     * 
     * @param id
     * @param menuCode
     * @return
     */
    @Override
    public boolean isExistMenuCode(String id, String menuCode) {
        if (StringUtils.isBlank(menuCode)) {
            return false;
        }
        return this.isExistByKw(new AppMenuVo(null, menuCode));
    }

    /**
     * 是否已存在该记录
     * 
     * @param vo
     * @return
     */
    private boolean isExistByKw(AppMenuVo vo) {
        String _id = vo.getId();
        if (StringUtils.isNotBlank(_id)) { // update
            AppMenuPo _oldPo = this.dao.get(_id);
            String _code = vo.getAppMenuCode();
            if (StringUtils.isNotBlank(_code) && _code.equals(_oldPo.getAppMenuCode())) {
                return false; // 1.没有改变
            }
        }
        // 2. save/update
        vo.setId(null);
        return (this.ecCount(vo) > 0);
    }

    @Override
    public AppMenuVo get(String id) {
        List<AppMenuVo> _list = this.ecList(new AppMenuVo(id), 1, 1, null);
        if (_list != null && !_list.isEmpty()) {
            return _list.get(0);
        }
        return null;
    }

    /**
     * 发布应用菜单
     * 
     * @param id
     * @param deployMenu
     * @throws Exception 
     */
    @Override
    public ErrorVo deployMenu(String id, IDeployMenu deployMenu) throws Exception {
        lock.lock();
        ErrorVo errorVo = new ErrorVo(-1);
        try {
            AppMenuVo vo = this.get(id);
            // 调用微信服务接口
            errorVo = deployMenu.doDeployMenu(vo);
            if (errorVo.isSuccess()) {
                // 改变状态
                AppMenuPo po = this.dao.get(id);
                po.setStatus(AppMenuConstants.StatusGc.S0.name());
                //
                this.dao.update(po);
                logger.info("发布菜单成功，菜单[{}] 应用[{}] \n", vo.getAppMenuName(), vo.getCliAppName());
            } else {
                logger.warn("发布菜单失败，菜单[{}] 应用[{}]，返回报文：{} \n", vo.getAppMenuName(), vo.getCliAppName(), errorVo);
            }
        } finally {
            lock.unlock();
        }
        return errorVo;
    }

}
