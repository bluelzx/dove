package com.gustz.dove.web.helper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.app.service.ClientAppService;
import com.sinovatech.rd.wcsb.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.cli.api.security.service.EncryptService;
import com.sinovatech.rd.wcsb.repo.app.dao.ClientAppDao;
import com.gustz.dove.web.base.ExtPtBaseController;

/**
 * 
 * TODO: 帮助C
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
@RequestMapping("/helper/*")
public class HelperController extends ExtPtBaseController {

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private ClientAppService clientAppService;

    @Autowired
    private ClientAppDao clientAppDao;

    /**
     * 微信服务助手
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/wcsbHelper")
    public String helper(HttpServletRequest request) throws Exception {
        return this.forward(request, null);
    }

    private ClientAppVo getClientApp(String cliAppCode) {
        List<ClientAppVo> _list = clientAppService.ecList(new ClientAppVo(null, null, cliAppCode), 1, 1, null);
        if (_list != null && _list.size() > 0) {
            return _list.get(0);
        }
        return null;
    }

    /**
     * 获取开发者token
     * 
     * @param request
     * @param cliAppCode
     * @return
     * @throws Exception
     */
    @RequestMapping("getDevToken")
    public @ResponseBody Map<?, ?> getDevToken(HttpServletRequest request, @RequestParam("cliAppCode") String cliAppCode)
            throws Exception {
        //
        ClientAppVo vo = this.getClientApp(cliAppCode);
        //
        final String devToken = encryptService.getDevToken(vo.getAccountCode(), vo.getWecAppId());
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, devToken);
    }

    /**
     * 获取开发者密钥AES
     * 
     * @param request
     * @param cliAppCode
     * @return
     * @throws Exception
     */
    @RequestMapping("getDevAesKeyt")
    public @ResponseBody Map<?, ?> getDevAesKeyt(HttpServletRequest request, @RequestParam("cliAppCode") String cliAppCode)
            throws Exception {
        //
        ClientAppVo vo = this.getClientApp(cliAppCode);
        //
        final String devAesKeyt = encryptService.getDevAesKeyt(cliAppCode, vo.getAccountCode(), vo.getWecAppId());
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, devAesKeyt);
    }

    /**
     * 获取客户端应用VO
     * 
     * @param request
     * @param cliAppCode
     * @return
     * @throws Exception
     */
    @RequestMapping("getCliAppVo")
    public @ResponseBody Map<?, ?> getCliAppVo(HttpServletRequest request, @RequestParam("cliAppCode") String cliAppCode)
            throws Exception {
        ClientAppVo vo = this.getClientApp(cliAppCode);
        //
        return New.chainMap().add(TAG_SUCCESS, true).add(TAG_DATA, vo);
    }

}
