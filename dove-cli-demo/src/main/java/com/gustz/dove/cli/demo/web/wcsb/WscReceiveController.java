package com.sinovatech.rd.wcsb.cli.demo.web.wcsb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinovatech.rd.wcsb.cli.sdk.base.service.ICliMsgReceiver;
import com.sinovatech.rd.wcsb.cli.sdk.web.WsCliReceiveSlet;

/**
 * TODO: 项目管理平台的微信消息接收C
 *
 * @author ZHENFENG ZHANG
 * @since [ Nov 8, 2015 ]
 */
@Controller
@RequestMapping("")
public class WscReceiveController extends WsCliReceiveSlet {

    private static final long serialVersionUID = 1L;

    //@Autowired
    //private ICliMsgReceiver cliMsgReceiver;

    @RequestMapping("/wcsbus/cli/receiveMsg")
    public void doCpOauth(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    /**
     * 获取客户消息接收服务
     * 
     * @return
     */
    @Override
    protected ICliMsgReceiver getCliMsgReceiver() {
        //return cliMsgReceiver;
        return null;
    }

}
