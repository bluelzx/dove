package com.sinovatech.rd.wcsb.cli.demo.web.addr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinovatech.rd.wcsb.cli.demo.addr.service.AddrListService;
import com.sinovatech.rd.wcsb.cli.demo.web.base.BaseController;

/**
 * 
 * TODO: 通讯录C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/addrlist/*")
public class AddrListController extends BaseController {

    private AddrListService addrListService;

    /**
     * 查询通讯录地址列表
     * 
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("listAddr")
    public String list(HttpServletRequest request, String id) throws Exception {
        System.out.println("查询通讯录地址列表...");
        addrListService = new AddrListService();
        //
        request.setAttribute("list", addrListService.listAddr());
        return request.getServletPath();
    }

}
