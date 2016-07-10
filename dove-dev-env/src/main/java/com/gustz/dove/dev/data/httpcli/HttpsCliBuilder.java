package com.gustz.dove.dev.data.httpcli;

import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sinovatech.fw.setup.pack.data.httpcli.AbstHttpsCliBuilder;

/**
 * TODO: HTTP service client builder
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 7, 2015]
 */
public class HttpsCliBuilder extends AbstHttpsCliBuilder implements ApplicationListener<ContextRefreshedEvent> {

    public void setPatternSet(Set<String> patternSet) {
        super.setPatternSet(patternSet);
    }

    public void setServiceBaseUrl(String serviceBaseUrl) {
        super.setServiceBaseUrl(serviceBaseUrl);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            this.buildCliXml(event);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
