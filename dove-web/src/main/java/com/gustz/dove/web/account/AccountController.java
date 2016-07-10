package com.gustz.dove.web.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gustz.dove.web.base.ExtBaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.account.service.AccountService;
import com.sinovatech.rd.wcsb.api.account.vo.AccountVo;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.repo.account.AccConstants;
import com.sinovatech.rd.wcsb.repo.dict.DictConstants;

/**
 * 
 * TODO: 账户C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/account/account/*")
public class AccountController extends ExtBaseController<AccountVo, AccountVo> {

    private AccountService service;

    @Autowired
    private DictService dictService;

    @Autowired
    public void needService(AccountService service) {
        super.setDataService(service);
        this.service = service;
    }

    @Override
    public int doCount(AccountVo searchForm) throws Exception {
        return this.service.ecCount(searchForm);
    }

    @Override
    public List<?> doList(AccountVo searchForm, int start, int limit, List<Order> orders) throws Exception {
        return this.service.ecList(searchForm, start, limit, orders);
    }

    /**
     * 账户的列表页
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("listAccount")
    public String list(HttpServletRequest request) throws Exception {
        Map<String, Object> _map = new HashMap<String, Object>();
        // 账户类型
        _map.put("ACC_TYPE", dictService.mapGroup(AccConstants.TypeGc.ACC_TYPE));
        // 状态
        _map.put("ACC_STATUS", dictService.mapGroup(DictConstants.RunStateGc.COMM_RUN_STATE));
        //
        return this.forward(request, _map);
    }

    /**
     * 是否存在唯一凭证
     * 
     * @param request
     * @param id
     * @param wecAppId
     * @return
     * @throws Exception
     */
    @RequestMapping("isExistAppId")
    public @ResponseBody Map<?, ?> isExistAppId(HttpServletRequest request, @RequestParam("id") String id,
            @RequestParam("wecAppId") String wecAppId) throws Exception {
        if (StringUtils.isNotBlank(wecAppId)) {
            wecAppId = this.base64ToStr(request, wecAppId);
        }
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistAppId(id, wecAppId));
    }

    /**
     * 是否存在凭证密钥
     * 
     * @param request
     * @param id
     * @param wecAppSecret
     * @return
     * @throws Exception
     */
    @RequestMapping("isExistAppSecret")
    public @ResponseBody Map<?, ?> isExistAppSecret(HttpServletRequest request, @RequestParam("id") String id,
            @RequestParam("wecAppSecret") String wecAppSecret) throws Exception {
        if (StringUtils.isNotBlank(wecAppSecret)) {
            wecAppSecret = this.base64ToStr(request, wecAppSecret);
        }
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistAppSecret(id, wecAppSecret));
    }

    /**
     * 获取账户类型
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("getAccType")
    public @ResponseBody Map<?, ?> getAccType() throws Exception {
        //
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, dictService.mapGroup(AccConstants.TypeGc.ACC_TYPE));
    }

    /**
     * 改变状态
     * 
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("chgStatus")
    public @ResponseBody Map<?, ?> chgStatus(@RequestParam("id") String id, @RequestParam("status") String status)
            throws Exception {
        this.service.chgStatus(id, status);
        //
        return New.chainMap().add(TAG_SUCCESS, true);
    }

    /**
     * 获取有效的账户
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("getActiveAcc")
    public @ResponseBody Map<?, ?> getActiveAcc() throws Exception {
        //
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.getActiveAcc());
    }

}
