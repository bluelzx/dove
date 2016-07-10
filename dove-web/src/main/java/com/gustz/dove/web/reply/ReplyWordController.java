package com.gustz.dove.web.reply;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.rd.wcsb.api.reply.service.ReplyWordService;
import com.sinovatech.rd.wcsb.api.reply.vo.ReplyWordVo;
import com.gustz.dove.web.base.ExtBaseController;

/**
 * 
 * TODO: 回复语C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/reply/replyWord/*")
public class ReplyWordController extends ExtBaseController<ReplyWordVo, ReplyWordVo> {

    private ReplyWordService service;

    @Autowired
    public void needService(ReplyWordService service) {
        super.setDataService(service);
        this.service = service;
    }

    @Override
    public int doCount(ReplyWordVo searchForm) throws Exception {
        return this.service.ecCount(searchForm);
    }

    @Override
    public List<?> doList(ReplyWordVo searchForm, int start, int limit, List<Order> orders) throws Exception {
        return this.service.ecList(searchForm, start, limit, orders);
    }

    /**
     * 回复语的列表页
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("listReplyWord")
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
        return null;
        //return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistGc(id, groupCode));
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

        return null;
        // return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, this.service.isExistGname(id, groupName));
    }

}
