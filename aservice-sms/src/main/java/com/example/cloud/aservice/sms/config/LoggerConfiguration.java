package com.example.cloud.aservice.sms.config;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;

@Service
public class LoggerConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(LoggerConfiguration.class);
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void configChangeListener(ConfigChangeEvent event) {
        refreshLoggingLevels();
    }

    @PostConstruct
    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (containsIngoreCase(key, LOGGER_TAG)) {
                String strLevel = config.getProperty(key,"info");
                LogLevel logLevel = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG,""),logLevel);
                logger.info("{},{}",key,strLevel);
            }
        }

    }


    /**
     * 配置的key中含有日志的标识
     * @param key 配置的key
     * @param loggerTag 日志标识 LOGGER_TAG
     * @return true:含有，false:不含有
     */
    private boolean containsIngoreCase(String key, String loggerTag) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(loggerTag)) {
            return false;
        }

        int len = loggerTag.length();
        int max = key.length() - len;
        for (int i = 0; i < max; i++) {
            if(key.regionMatches(true,i,loggerTag,0,len)){
                return true;
            }
        }
        return false;
    }
}
