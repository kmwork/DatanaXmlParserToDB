package ru.datana.steel.parser.config;

import java.time.LocalDateTime;

public class DbConst {
    public static final String DB_SCHEMA = "datalake";
    public static final String DB_CATALOG = "postgres";
    public static final Integer DEFAULT_TIMEOUT = 5000;
    public static final Boolean PERMANENT_CONNECTION_FLAG = Boolean.FALSE;
    public static final int DEFAULT_MASK = 0;

    public static final LocalDateTime SAVE_TIME = LocalDateTime.now();

}
