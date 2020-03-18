package ru.datana.steel.parser;


import lombok.extern.slf4j.Slf4j;
import ru.datana.steel.parser.config.AppConts;
import ru.datana.steel.parser.config.AppOptions;
import ru.datana.steel.parser.utils.XmlUtil;
import ru.datana.steel.parser.xml.pojo.ItemsType;
import ru.datana.steel.parser.xml.pojo.RootType;

import java.io.File;
import java.util.Arrays;

@Slf4j
public class ControllerS7XmlParserApp {


    public static void main(String[] args) {
        log.info(AppConts.APP_LOG_PREFIX + "================ Запуск  ================. Аргументы = " + Arrays.toString(args));


        try {
            AppOptions appOptions = new AppOptions();
            appOptions.load();
            File xmlS7RootFile = new File(appOptions.getDataFileDir(), AppConts.S7_ROOT_CONFIG_FILE_NAME);
            RootType rootConfig = XmlUtil.xmlFileToObject(xmlS7RootFile, RootType.class);


            File xmlS7DbFile = new File(appOptions.getDataFileDir() + File.separator + "UPK2" + File.separator + "PLC_LA" + File.separator + "db601.xml");
            ItemsType items = XmlUtil.xmlFileToObject(xmlS7RootFile, ItemsType.class);
        } catch (Exception ex) {
            log.error(AppConts.ERROR_LOG_PREFIX + " Ошибка в программе", ex);
        }
        log.info(AppConts.APP_LOG_PREFIX + "********* Завершение программы *********");
    }

}
