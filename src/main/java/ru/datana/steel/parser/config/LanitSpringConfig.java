package ru.datana.steel.parser.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;


@Configuration
@EnableBatchProcessing
@ConfigurationProperties(prefix = "datana.global")
@Slf4j
@EnableTransactionManagement
@ComponentScan({"ru.datana.steel"})
public class LanitSpringConfig {

    private static final String LOG_PREFIX = "[LanitSpringConfig] ";
    @Getter
    @Setter
    protected String appVersion;

    @Getter
    @Setter
    protected String dataDir;

    @PostConstruct
    protected void postConstruct() {
        log.info(LOG_PREFIX + "Версия XML Парсера для ММК: " + appVersion);
        log.info(LOG_PREFIX + "Папка с xml указана как: " + dataDir);
    }
}