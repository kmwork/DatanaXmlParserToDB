package ru.datana.steel.parser;


import lombok.extern.slf4j.Slf4j;
import ru.datana.steel.parser.config.AppConts;
import ru.datana.steel.parser.utils.XmlUtil;
import ru.datana.steel.parser.xml.pojo.RootType;

import java.io.File;
import java.util.Arrays;

@Slf4j
public class ControllerS7XmlParserApp {

    public static void main(String[] args) {
        log.info(AppConts.APP_LOG_PREFIX + "================ Запуск  ================. Аргументы = " + Arrays.toString(args));
        try {
            File xmlFile = new File("/home/lin/work-lanit/S7ParserXML-K6/DATA/config-plc.xml");
            RootType rootConfig = XmlUtil.xmlFileToObject(xmlFile, RootType.class);
        } catch (Exception ex) {
            log.error(AppConts.ERROR_LOG_PREFIX + " Ошибка в программе", ex);
        }
        log.info(AppConts.APP_LOG_PREFIX + "********* Завершение программы *********");
    }

}
