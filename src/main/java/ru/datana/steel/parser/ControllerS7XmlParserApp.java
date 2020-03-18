package ru.datana.steel.parser;


import lombok.extern.slf4j.Slf4j;
import ru.datana.steel.parser.config.AppConts;

import java.util.Arrays;

@Slf4j
public class ControllerS7XmlParserApp {

    public static void main(String[] args) {
        log.info(AppConts.APP_LOG_PREFIX + "================ Запуск  ================. Аргументы = " + Arrays.toString(args));
    }


}
