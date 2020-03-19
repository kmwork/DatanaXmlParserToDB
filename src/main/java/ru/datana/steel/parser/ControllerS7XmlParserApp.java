package ru.datana.steel.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.datana.steel.parser.config.AppConst;

import java.io.File;
import java.util.Properties;

@Slf4j
@SpringBootApplication
public class ControllerS7XmlParserApp {
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(S7AppRunner.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    private static void extConfigure() {
        String configLocation = System.getProperty(AppConst.SYS_DIR_PROP); //get the default config directory location
        if (StringUtils.isEmpty(configLocation)) {
            log.error(AppConst.APP_LOG_PREFIX + "Путь к настройкам не указан по java.options по имени свойства =  " + AppConst.SYS_DIR_PROP);
            System.exit(-100);
        }
        File configPath = new File(configLocation, AppConst.EXT_CONFIG_NAME);
        log.info(AppConst.APP_LOG_PREFIX + "[Config]: configPath = " + configPath);
        log.info(AppConst.APP_LOG_PREFIX + "Настройка приложения");

        if (configPath.exists()) {
            Properties props = System.getProperties();
            props.setProperty("spring.config.location", configPath.getAbsolutePath()); //set the config file to use
            props.setProperty("spring.profiles.active", AppConst.DB_POSTGRES_PROFILE);
            props.setProperty("java.awt.headless", "false");
            props.setProperty("file.encoding", "UTF8");
        } else {
            log.error(AppConst.ERROR_LOG_PREFIX + "Конфиг не найден по пути " + configPath.getAbsolutePath());
            System.exit(-200);
        }
    }
}

