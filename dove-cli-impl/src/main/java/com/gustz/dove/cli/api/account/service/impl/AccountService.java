package com.gustz.dove.cli.api.account.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.dao.SqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.gustz.dove.cli.api.account.vo.AccountVo;
import com.gustz.dove.cli.api.service.util.ClientConstants;
import com.sinovatech.rd.wcsb.repo.account.dao.AccountDao;
import com.sinovatech.rd.wcsb.repo.account.po.AccountPo;

/**
 * 
 * TODO: 账户服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class AccountService extends AbstractDataService<AccountVo, AccountPo, String> {

    @Autowired
    private SqlDao sqlDao;

    @Autowired
    public void needDao(AccountDao dao) {
        super.setDao(dao);
        // this.dao = dao;
    }

    @Override
    public String getId(AccountVo vo) {
        return vo.getId();
    }

    /**
     * 按客户端AppCode+账号查询信息
     * 
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    public AccountVo getByCaCode(String cliAppCode, String devAcCode) {
        if (StringUtils.isBlank(cliAppCode) || StringUtils.isBlank(devAcCode)) {
            return null;
        }
        String sql = "select t.* from WCSB_ACCOUNT t,WCSB_CLIENT_APP t1 where t.ACCOUNT_CODE=t1.ACCOUNT_CODE ";
        QueryInfo info = QueryInfoBuilder.ins(sql) //
                .andEq("t.IS_DELETE", ClientConstants.NO) //
                .andEq("t1.IS_DELETE", ClientConstants.NO) //
                .andEq("t.ACCOUNT_CODE", devAcCode) //
                .andEq("t1.CLI_APP_CODE", cliAppCode) //
                .build();
        //
        AccountVo vo = sqlDao.unique(info.getSql(), AccountVo.class, info.getParArr());
        return vo;
    }

}
