package ru.datana.steel.parser.config;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.datana.steel.parser.utils.AppException;
import ru.datana.steel.parser.utils.LanitFileUtils;
import ru.datana.steel.parser.utils.ValueParser;

import java.util.Properties;

/**
 * POJO для конфига из парсинга конфига datana_xml_parser.properties
 */
@ToString
@Slf4j
public class AppOptions {

    @Getter
    private String dataFileDir;

    @Getter
    private String appVersion;

    public void load() throws AppException {
        Properties p = LanitFileUtils.readDataConfig();
        appVersion = ValueParser.readPropAsText(p, "app.version");
        dataFileDir = ValueParser.readPropAsText(p, "data.file.dir", false);

        log.info("[Настройки] Параметры = " + toString());

    }

}
