package com.gustz.dove.web.dict;

import java.util.List;
import java.util.Map;

import com.gustz.dove.web.base.ExtBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictVo;

/**
 * 
 * TODO: 数据字典C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/dict/dict/*")
public class DictController extends ExtBaseController<DictVo, DictVo> {

    private DictService dictService;

    @Autowired
    public void needService(DictService service) {
        super.setDataService(service);
        this.dictService = service;
    }

    @Override
    public int doCount(DictVo searchForm) throws Exception {
        return this.dictService.ecCount(searchForm);
    }

    @Override
    public List<?> doList(DictVo searchForm, int start, int limit, List<Order> orders) throws Exception {
        return this.dictService.ecList(searchForm, start, limit, orders);
    }

    /**
     * 是否已存在KEY
     * 
     * @param id
     * @param groupCode
     * @param dataKey
     * @return
     */
    @RequestMapping("isExistKey")
    public @ResponseBody Map<?, ?> isExistKey(@RequestParam("id") String id, @RequestParam("groupCode") String groupCode,
            @RequestParam("dataKey") String dataKey) throws Exception {
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.dictService.isExistKey(id, groupCode, dataKey));
    }

    /**
     * 是否已存在VALUE
     * 
     * @param id
     * @param groupCode
     * @param dataValue
     * @return
     */
    @RequestMapping("isExistValue")
    public @ResponseBody Map<?, ?> isExistValue(@RequestParam("id") String id, @RequestParam("groupCode") String groupCode,
            @RequestParam("dataValue") String dataValue) throws Exception {
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.dictService.isExistValue(id, groupCode, dataValue));
    }

    /**
     * refresh dict
     * 
     * @return
     */
    @RequestMapping("refreshDict")
    public @ResponseBody Map<?, ?> refreshDict() throws Exception {
        this.dictService.refreshDict();
        return New.chainMap().add(TAG_SUCCESS, true);
    }

}
