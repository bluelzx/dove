package com.gustz.dove.cli.api.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

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
import com.gustz.dove.cli.api.app.vo.ClientAppVo;
import com.gustz.dove.cli.api.service.util.ClientConstants;
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
public class ClientAppService extends AbstractDataService<ClientAppVo, ClientAppPo, String> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlDao sqlDao;

    private ClientAppDao dao;

    private final Lock lock = new ReentrantLock();

    private ConcurrentMap<String, ClientAppVo> cacheMap = new ConcurrentHashMap<String, ClientAppVo>();

    @Autowired
    public void needDao(ClientAppDao dao) {
        super.setDao(dao);
        this.dao = dao;
    }

    public String getId(ClientAppVo vo) {
        return vo.getId();
    }

    /**
     * Init data
     */
    @PostConstruct
    private void init() {
        List<ClientAppVo> _list = this.listActive();
        if (_list == null || _list.isEmpty()) {
            logger.warn("Client app data list is empty.");
            return;
        }
        for (ClientAppVo vo : _list) {
            if (vo == null) {
                continue;
            }
            String accountCode = vo.getAccountCode();
            String cliAppCode = vo.getCliAppCode();
            String websCodes = vo.getWebsCodes();
            String receiveUrl = vo.getReceiveUrl();
            String oauthCbUrl = vo.getOauthCbUrl();
            if (StringUtils.isBlank(accountCode) || StringUtils.isBlank(cliAppCode) || StringUtils.isBlank(websCodes)
                    || StringUtils.isBlank(receiveUrl) || StringUtils.isBlank(oauthCbUrl)) {
                logger.error("Client app [{}]: accountCode/cliAppCode/websCodes/receiveUrl/oauthCbUrl is empty!.",
                        vo.getCliAppName());
                continue;
            }
            cacheMap.put(vo.getAccountCode(), vo);
            //
            logger.info("客户端应用[ {} ]已被成功加载到缓存中 \n", vo.getCliAppName());
        }
    }

    public void reloadCliApp() {
        lock.lock();
        try {
            this.init();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 查询全部活动的应用
     * 
     * @return
     */
    public List<ClientAppVo> listActive() {
        //
        return this.listAll(new ClientAppVo(null, AppConstants.StatusGc.S0.name()));
    }

    /**
     * 查询全部
     * 
     * @param search
     * @return 
     */
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
    private List<ClientAppVo> ecList(ClientAppVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();
        //
        List<ClientAppVo> _list = sqlDao.list(queryInfo.getSql(), start, limit, ClientAppVo.class, queryInfo.getParArr());
        return _list;
    }

    private QueryInfoBuilder prepareQuery(ClientAppVo search) {
        String hql = "select t.*,t1.ACCOUNT_NAME,t1.WEC_APP_ID,t1.WEC_APP_SECRET from WCSB_CLIENT_APP t,WCSB_ACCOUNT t1 where t.ACCOUNT_CODE=t1.ACCOUNT_CODE ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(hql) //
                .andEq("t.IS_DELETE", ClientConstants.NO) //
                .andEq("t.CLI_APP_CODE", search.getCliAppCode()) // 编码
                //
                .andContains("t.CLI_APP_NAME", search.getCliAppName()) // 名称
                .andIn("t.WEBS_CODES", search.getWebsCodesIn()) // 服务编码集
                .andEq("t.STATUS", search.getStatus()) // 状态
                .andIn("t.STATUS", search.getStatusIn()) // 状态多个
                .andEq("t.ACCOUNT_CODE", search.getAccountCode()) // 账号
                .andEq("t.IS_REPLY_MSG", search.getIsReplyMsg())
                //
                .andEq("t1.IS_DELETE", ClientConstants.NO) //
                .andEq("t1.STATUS", DictConstants.RunStateGc.S0.name()) // 账户状态：S0已启用
                // 
                .orderDesc("t.CREATE_TIME");

        return builder;
    }

    /**
     * 获取当前接入的客户端APP
     * 
     * <pre>
     * 状态为S99的客户端APP（唯一）
     * </pre>
     * @return
     */
    public ClientAppVo getCurrCliApp() {
        List<ClientAppVo> _list = this.ecList(new ClientAppVo(null, AppConstants.StatusGc.S99.name()), 1, 1, null);
        if (_list != null && !_list.isEmpty()) {
            return _list.get(0);
        }
        return null;
    }

    /**
     * 启用客户端APP
     */
    public void enableCliApp() {
        lock.lock();
        try {
            ClientAppVo _appVo = this.getCurrCliApp();
            if (_appVo != null) { // 启用
                ClientAppPo po = this.dao.get(_appVo.getId());
                po.setStatus(AppConstants.StatusGc.S0.name());
                //
                this.dao.update(po);
                // 更新缓存数据
                this.cacheMap.put(_appVo.getAccountCode(), _appVo);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取缓存中可用的客户端应用
     * 
     * @return key=AccountCode value=ClientAppVo
     */
    public ConcurrentMap<String, ClientAppVo> getActiveCacheByAcc() {
        this.lock.lock();
        try {
            if (this.cacheMap.isEmpty()) {
                this.init();
            }
        } finally {
            this.lock.unlock();
        }
        return this.cacheMap;
    }

    /**
     * 获取有效的客户端应用
     * 
     * @return key=CliAppCode value=ClientAppVo
     */
    public Map<String, ClientAppVo> getActiveCliApp() {
        Map<String, ClientAppVo> _map = new HashMap<String, ClientAppVo>();
        //
        Map<String, ClientAppVo> _cacheMap = this.getActiveCacheByAcc();
        if (_cacheMap != null && _cacheMap.size() > 0) {
            for (Map.Entry<String, ClientAppVo> _entry : _cacheMap.entrySet()) {
                _map.put(_entry.getValue().getCliAppCode(), _entry.getValue());
            }
        }
        _cacheMap = null;
        return _map;
    }

    /**
     * 校验客户端APP
     * 
     * @param vo
     * @return
     */
    public void checkCliApp(final ClientAppVo vo) {
        // 客户端APP编码 1.
        final String cliAppCode = vo.getCliAppCode();
        this.hasRequired("cliAppCode", cliAppCode);

        // 客户端APP密码 2.
        final String cliAppPwd = vo.getCliAppPwd();
        this.hasRequired("cliAppPwd", cliAppPwd);

        // 开发者账号 3.
        String accountCode = vo.getAccountCode();
        this.hasRequired("accountCode", accountCode);

        // 服务编码（请求服务的接口编码）4. TODO
        // String websCodes = vo.getWebsCodes();
        // this.hasRequired("websCodes", websCodes);

        // 客户端IP地址 5.
        String[] cliIpAddrs = vo.getCliIpAddrsIn();
        this.hasRequireds("cliIpAddrs", cliIpAddrs);

        // --------------- 授权校验 --------------- begin
        ClientAppVo _vo2 = this.getActiveCliApp().get(cliAppCode); // 1.
        if (_vo2 == null) {
            throw new IllegalArgumentException("Client app is not authorized.");
        }
        if (!cliAppPwd.equals(_vo2.getCliAppPwd())) {
            throw new IllegalArgumentException("Client password is error.");
        }
        if (!accountCode.equals(_vo2.getAccountCode())) {
            throw new IllegalArgumentException("Client dev account code is not authorized.");
        }
        // 服务编码（请求服务的接口编码）TODO
        // String[] _tgWebsCodes = _vo2.getWebsCodes().split(",");
        // if (!this.isContain(websCodes.split(","), _tgWebsCodes)) {
        //     throw new IllegalArgumentException(String.format("Web service code[ %1$s ] is not authorized.", websCodes));
        // }
        // IPs -- begin
        String[] _tgCliIpAddrs = _vo2.getCliIpAddrs().split(",");
        if (!this.isContain(cliIpAddrs, _tgCliIpAddrs)) {
            throw new IllegalArgumentException("Client IP address list is not authorized.");
        }
        // IPs -- end
        // --------------- 授权校验 --------------- end

    }

    /**
     * Is contain
     * 
     * @param srcs
     * @param tgs
     * @return
     */
    private boolean isContain(String[] srcs, String[] tgs) {
        for (String _ts : tgs) {
            for (String _ss : srcs) {
                if (_ts.equals(_ss)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Has required
     * 
     * @param field
     * @param data
     */
    private void hasRequired(final String field, final String data) {
        if (StringUtils.isBlank(data)) {
            throw new IllegalArgumentException(String.format("Arg '%1$s[ %2$s ]' is required.", field, data));
        }
    }

    /**
     * Has required
     * 
     * @param field
     * @param datas
     */
    private void hasRequireds(final String field, final String[] datas) {
        if (datas == null || datas.length == 0) {
            throw new IllegalArgumentException(String.format("Arg '%1$s[ null ]' is required.", field));
        }
    }

}
