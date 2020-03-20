package ru.datana.steel.parser.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Константы программы
 */
public class AppConst {

    public static final String SYS_DIR_PROP = "app.dir";
    public static final String SYS_PROFILE_PROP = "app.profile";
    public static final Charset ENCODING = StandardCharsets.UTF_8;
    public final static String EXT_DEV_CONFIG_NAME = "application-dev_postgres.yaml";
    public final static String EXT_REMOTE_CONFIG_NAME = "application-remote_postgres.yaml";

    public static final String SUCCESS_LOG_PREFIX = "[App-Успешно] ";
    public static final String ERROR_LOG_PREFIX = "[App-Ошибка] ";
    public static final String APP_LOG_PREFIX = "[App-XMLParser-Danata] ";

    public static final String S7_ROOT_CONFIG_FILE_NAME = "config-plc.xml";

    public static final String S7_REG_EXPRESSION_DB_NAME = "^db[0-9]+\\.xml$";

    public static final String DB_DEV_POSTGRES_PROFILE = "dev_postgres";
    public static final String DB_REMOTE_POSTGRES_PROFILE = "remote_postgres";

}
