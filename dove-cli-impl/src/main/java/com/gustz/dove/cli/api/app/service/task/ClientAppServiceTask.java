package com.gustz.dove.cli.api.app.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gustz.dove.cli.api.app.service.impl.ClientAppService;

/**
 * TODO: 客户端应用的定时任务
 *
 * @author ZHENFENG ZHANG
 * @since  [Mar 22, 2015]
 */
@Component
@Transactional(readOnly = true)
public class ClientAppServiceTask {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientAppService clientAppService;

    /**
     * 重新加载客户端应用的缓存
     * <pre>
     * 每隔10分钟扫描一次
     * </pre>
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void reloadCliApp() {
        try {
            clientAppService.reloadCliApp();
        } catch (Exception e) {
            logger.error("重新加载客户端应用的缓存异常，退出本次操作。", e);
        }
    }

}
