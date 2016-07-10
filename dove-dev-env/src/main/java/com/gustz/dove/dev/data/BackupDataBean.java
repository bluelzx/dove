package com.gustz.dove.dev.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sinovatech.fw.setup.pack.data.resolver.BaseBackupResolver;

/**
 * TODO: Backup data bean
 *
 * @author ZHENFENG ZHANG
 * @since  [May 10, 2015]
 */
public class BackupDataBean extends BaseBackupResolver implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private void needSqlDao(SqlDaoBuilder builder) {
        super.setBaseSqlDao(builder);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            this.doBackupData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("", e);
        }
    }

}
