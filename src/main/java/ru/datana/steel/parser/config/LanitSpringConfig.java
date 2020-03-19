package ru.datana.steel.parser.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;


@Configuration
@EnableBatchProcessing
@ConfigurationProperties(prefix = "datana.global")
@Slf4j
@EnableTransactionManagement
public class LanitSpringConfig {

    private static final String LOG_PREFIX = "[LanitSpringConfig] ";
    @Getter
    @Setter
    protected String appVersion;

    @Getter
    @Setter
    protected String dataFileDir;

    @PostConstruct
    protected void postConstruct() {
        log.info(LOG_PREFIX + "Версия XML Парсера для ММК: " + appVersion);
        log.info(LOG_PREFIX + "Папка с xml указана как: " + dataFileDir);
    }


//    @Bean
//    public PropertySource<?> yamlPropertySourceLoader() throws IOException {
//        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
//        PropertySource<?> applicationYamlPropertySource = loader.load("application.yml",
//                new ClassPathResource("application.yml"), "default");
//        return applicationYamlPropertySource;
//    }

}