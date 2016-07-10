package com.gustz.dove.web.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gustz.dove.web.base.ExtBaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.ErrorVo;
import com.sinovatech.rd.wcsb.api.menu.AppMenuConstants;
import com.sinovatech.rd.wcsb.api.menu.service.AppMenuService;
import com.sinovatech.rd.wcsb.api.menu.service.AppMenuService.IDeployMenu;
import com.sinovatech.rd.wcsb.api.menu.vo.AppMenuVo;
import com.sinovatech.rd.wcsb.cli.api.menu.req.MenuReq;
import com.sinovatech.rd.wcsb.cli.api.menu.vo.Menu;
import com.sinovatech.rd.wcsb.cli.api.service.util.JsonMapper;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.menu.service.MenuCpService;
import com.sinovatech.rd.wcsb.mpcli.api.menu.service.MenuMpService;
import com.sinovatech.rd.wcsb.repo.account.AccConstants;

/**
 * 
 * TODO: 应用菜单C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/menu/appMenu/*")
public class AppMenuController extends ExtBaseController<AppMenuVo, AppMenuVo> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuCpService menuCpService;

    @Autowired
    private MenuMpService menuMpService;

    @Autowired
    private DictService dictService;

    private AppMenuService service;

    @Autowired
    public void needService(AppMenuService service) {
        super.setDataService(service);
        this.service = service;
    }

    @Override
    public int doCount(AppMenuVo searchForm) throws Exception {
        return this.service.ecCount(searchForm);
    }

    @Override
    public List<?> doList(AppMenuVo searchForm, int start, int limit, List<Order> orders) throws Exception {
        return this.service.ecList(searchForm, start, limit, orders);
    }

    /**
     * 应用菜单的列表页
     * 
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("listAppMenu")
    public String list(HttpServletRequest request) throws Exception {
        Map<String, Object> _map = new HashMap<String, Object>();
        // 状态
        _map.put("APP_MENU_STATUS", dictService.mapGroup(AppMenuConstants.StatusGc.APP_MENU_STATUS));
        //
        return this.forward(request, _map);
    }

    /**
     * 是否存在菜单编码
     * 
     * @param request
     * @param id
     * @param wecAppId
     * @return
     * @throws Exception
     */
    @RequestMapping("isExistMenuCode")
    public @ResponseBody Map<?, ?> isExistMenuCode(HttpServletRequest request, @RequestParam("id") String id,
            @RequestParam("appMenuCode") String appMenuCode) throws Exception {
        if (StringUtils.isNotBlank(appMenuCode)) {
            appMenuCode = this.base64ToStr(request, appMenuCode);
        }
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistMenuCode(id, appMenuCode));
    }

    /**
     * 发布应用菜单
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("deployAppMenu")
    public @ResponseBody Map<?, ?> deployAppMenu(@RequestParam("id") String id) throws Exception {
        final ErrorVo vo = this.service.deployMenu(id, new IDeployMenu() {
            @Override
            public ErrorVo doDeployMenu(final AppMenuVo appMenuVo) throws Exception {
                ErrorVo errorVo = new ErrorVo();
                try {
                    // 菜单主体
                    final Menu body = JsonMapper.getObjectMapper().readValue(appMenuVo.getContent(), Menu.class);
                    final MenuReq req = new MenuReq(appMenuVo.getAccountCode(), body);
                    //
                    CommRsp rsp = null;
                    final long sn = System.currentTimeMillis();
                    final String cliAppCode = appMenuVo.getCliAppCode();
                    if (AccConstants.TypeGc.T2.name().equals(appMenuVo.getAccountType())) { // 企业号
                        rsp = menuCpService.createMenu(sn, cliAppCode, req);
                    } else { // 订阅号/服务号
                        rsp = menuMpService.createMenu(sn, cliAppCode, req);
                    }
                    //
                    errorVo.setErrCode(rsp.getBody().getErrCode());
                    errorVo.setErrMsg(rsp.getBody().getErrMsg());
                } catch (Exception e) {
                    logger.warn("", e);
                    throw e;
                }
                return errorVo;
            }
        });
        //
        return New.chainMap().add(TAG_SUCCESS, vo.isSuccess()).add(TAG_DATA, vo.getErrMsg());
    }

}
