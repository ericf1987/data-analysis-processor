package com.fzzq.data.analysis.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by fengye on 2017/8/7.
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class DataAnalysisApplication {

    private static final Logger LOG = LoggerFactory.getLogger(DataAnalysisApplication.class);

    public static void main(String[] args) {

        LOG.info("======容器开始启动======");

        new SpringApplication(DataAnalysisApplication.class).run(args);

        LOG.info("======容器启动完成======");
    }

}
