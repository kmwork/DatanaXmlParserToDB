package ru.datana.steel.parser.config;

/**
 * Константы программы
 */
public class AppConst {

    public static final String CONF_FILE_NAME = "datana_xml_parser.properties";
    public static final String SYS_DIR_PROP = "app.dir";
    public static final String ENCODING = "UTF8";
    public final static String EXT_CONFIG_NAME = "datana-xml-parser-config.yaml";

    public static final String SUCCESS_LOG_PREFIX = "[App-Успешно] ";
    public static final String ERROR_LOG_PREFIX = "[App-Ошибка] ";
    public static final String APP_LOG_PREFIX = "[App-XMLParser-Danata] ";

    public static final String S7_ROOT_CONFIG_FILE_NAME = "config-plc.xml";

    public static final String S7_REG_EXPRESSION_DB_NAME = "^db[0-9]+\\.xml$";

    public static final String DB_POSTGRES_PROFILE = "datana_db_postgres";


}
