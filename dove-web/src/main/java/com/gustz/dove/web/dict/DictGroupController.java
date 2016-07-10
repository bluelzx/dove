package com.gustz.dove.web.dict;

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
import com.sinovatech.rd.wcsb.api.dict.service.DictGroupService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo;

/**
 * 
 * TODO: 字典组别C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/dict/dictGroup/*")
public class DictGroupController extends ExtBaseController<DictGroupVo, DictGroupVo> {

    private DictGroupService service;

    @Autowired
    public void needService(DictGroupService service) {
        super.setDataService(service);
        this.service = service;
    }

    @Override
    public int doCount(DictGroupVo searchForm) throws Exception {
        return this.service.ecCount(searchForm);
    }

    @Override
    public List<?> doList(DictGroupVo searchForm, int start, int limit, List<Order> orders) throws Exception {
        return this.service.ecList(searchForm, start, limit, orders);
    }

    /**
     * 字典组别的列表页
     * 
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("listDictGroup")
    public String list(HttpServletRequest request) throws Exception {
        return this.forward(request, null);
    }

    /**
     * 是否已存在该记录-组别编码
     * 
     * @param request
     * @param id
     * @param groupCode
     * @return
     * @throws Exception
     */
    @RequestMapping("isExistGc")
    public @ResponseBody Map<?, ?> isExistGc(HttpServletRequest request, @RequestParam("id") String id,
            @RequestParam("groupCode") String groupCode) throws Exception {
        if (StringUtils.isNotBlank(groupCode)) {
            groupCode = this.base64ToStr(request, groupCode);
        }
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistGc(id, groupCode));
    }

    /**
     * 是否已存在该记录-组别名称
     * 
     * @param request
     * @param id
     * @param groupName
     * @return
     * @throws Exception
     */
    @RequestMapping("isExistGname")
    public @ResponseBody Map<?, ?> isExistGname(HttpServletRequest request, @RequestParam("id") String id,
            @RequestParam("groupName") String groupName) throws Exception {
        if (StringUtils.isNotBlank(groupName)) {
            groupName = this.base64ToStr(request, groupName);
        }
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistGname(id, groupName));
    }

}
