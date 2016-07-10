package com.gustz.dove.web.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.app.service.ClientAppService;
import com.sinovatech.rd.wcsb.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.repo.app.AppConstants;
import com.sinovatech.rd.wcsb.repo.dict.DictConstants;
import com.gustz.dove.web.base.ExtBaseController;

/**
 * 
 * TODO: 客户端应用C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/app/clientApp/*")
public class ClientAppController extends ExtBaseController<ClientAppVo, ClientAppVo> {

    private ClientAppService service;

    @Autowired
    private DictService dictService;

    @Autowired
    public void needService(ClientAppService service) {
        super.setDataService(service);
        this.service = service;
    }

    @Override
    public int doCount(ClientAppVo searchForm) throws Exception {
        return this.service.ecCount(searchForm);
    }

    @Override
    public List<?> doList(ClientAppVo searchForm, int start, int limit, List<Order> orders) throws Exception {
        return this.service.ecList(searchForm, start, limit, orders);
    }

    /**
     * 客户端应用的列表页
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("listClientApp")
    public String list(HttpServletRequest request) throws Exception {
        Map<String, Object> _map = new HashMap<String, Object>();
        // 是否回复
        _map.put("COMM_YN_TYPE", dictService.mapGroup(DictConstants.YnTypeGc.COMM_YN_TYPE));
        // 状态
        _map.put("APP_STATUS", dictService.mapGroup(AppConstants.StatusGc.APP_STATUS));
        //
        return this.forward(request, _map);
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
     * 改变为开发接入状态
     * 
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("chgDevStatus")
    public @ResponseBody Map<?, ?> chgDevStatus(@RequestParam("ids") String[] ids) throws Exception {
        this.service.chgDevStatus(ids[0]);
        //
        return New.chainMap().add(TAG_SUCCESS, true);
    }

    /**
     * 获取有效的客户端应用名称
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("getActiCliAppName")
    public @ResponseBody Map<?, ?> getActiCliAppName() throws Exception {
        //
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.getActiCliAppName());
    }

}
