--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7 (Ubuntu 11.7-2.pgdg18.04+1)
-- Dumped by pg_dump version 11.7 (Ubuntu 11.7-2.pgdg18.04+1)

-- Started on 2020-03-20 11:45:02 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 9 (class 2615 OID 16395)
-- Name: datalake; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA datalake;


ALTER SCHEMA datalake OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 16396)
-- Name: controllers; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.controllers
(
    id                   integer               NOT NULL,
    controller_name      character varying(50) NOT NULL,
    rack                 integer DEFAULT 0     NOT NULL,
    slot                 integer DEFAULT 0     NOT NULL,
    ip                   character varying(15) NOT NULL,
    write_enable         boolean DEFAULT false NOT NULL,
    permanent_connection boolean DEFAULT true  NOT NULL,
    timeout              integer DEFAULT 5000  NOT NULL
);


ALTER TABLE datalake.controllers
    OWNER TO postgres;

--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE controllers; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.controllers IS 'Настройки соединения с контроллерами';


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.id IS 'Идентификатор';


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.controller_name; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.controller_name IS 'Наименование контроллера';


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.rack; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.rack IS 'Корзина';


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.slot; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.slot IS 'Место в корзине';


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.ip; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.ip IS 'IP адрес контроллера';


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.write_enable; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.write_enable IS 'Разрешить запись в dbblock контроллера';


--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.permanent_connection; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.permanent_connection IS 'Удерживать постоянное соединение';


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN controllers.timeout; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers.timeout IS 'Время таймаута для повторного соединения в мс';


--
-- TOC entry 199 (class 1259 OID 16403)
-- Name: controllers_id_seq; Type: SEQUENCE; Schema: datalake; Owner: postgres
--

CREATE SEQUENCE datalake.controllers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datalake.controllers_id_seq
    OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16405)
-- Name: controllers_data; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.controllers_data
(
    id            integer                     DEFAULT nextval('datalake.controllers_id_seq'::regclass) NOT NULL,
    description   character varying(255),
    unit_id       integer                                                                              NOT NULL,
    controller_id integer                                                                              NOT NULL,
    data_block    integer                                                                              NOT NULL,
    data_type     character varying(10)                                                                NOT NULL,
    data_offset   integer                                                                              NOT NULL,
    bitmask       character varying(8),
    rec_dt        timestamp without time zone DEFAULT CURRENT_TIMESTAMP                                NOT NULL,
    sign          boolean                     DEFAULT false                                            NOT NULL
);


ALTER TABLE datalake.controllers_data
    OWNER TO postgres;

--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 200
-- Name: TABLE controllers_data; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.controllers_data IS 'Таблица описания контроллеров и датчиков';


--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.id IS 'Внутренний идентификатор записи';


--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.description; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.description IS 'Описание данных в контроллере';


--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.unit_id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.unit_id IS 'Идентификатор агригата';


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.controller_id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.controller_id IS 'Идентификатор контроллера';


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.data_block; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.data_block IS 'Номер дата блока';


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.data_type; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.data_type IS 'Тип данных дата блоке';


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.data_offset; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.data_offset IS 'Сдвиг в байтах относительно начала блока данных';


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.bitmask; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.bitmask IS 'Маска бита, который нужно получить';


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.rec_dt; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.rec_dt IS 'Дата и время вставки или изменения записи';


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN controllers_data.sign; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_data.sign IS 'Знакове число (может быть минус)';


--
-- TOC entry 201 (class 1259 OID 16410)
-- Name: controllers_id_seq1; Type: SEQUENCE; Schema: datalake; Owner: postgres
--

CREATE SEQUENCE datalake.controllers_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datalake.controllers_id_seq1
    OWNER TO postgres;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 201
-- Name: controllers_id_seq1; Type: SEQUENCE OWNED BY; Schema: datalake; Owner: postgres
--

ALTER SEQUENCE datalake.controllers_id_seq1 OWNED BY datalake.controllers.id;


--
-- TOC entry 202 (class 1259 OID 16412)
-- Name: controllers_tasks; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.controllers_tasks
(
    id          integer                                NOT NULL,
    description character varying(255)                 NOT NULL,
    is_active   boolean,
    task_guid   uuid DEFAULT public.uuid_generate_v1() NOT NULL
);


ALTER TABLE datalake.controllers_tasks
    OWNER TO postgres;

--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE controllers_tasks; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.controllers_tasks IS 'Задачи для чтения контроллеров';


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN controllers_tasks.id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_tasks.id IS 'Идентификатор задачи';


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN controllers_tasks.description; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_tasks.description IS 'Описание задачи';


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN controllers_tasks.is_active; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_tasks.is_active IS 'Признак активной задачи';


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN controllers_tasks.task_guid; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_tasks.task_guid IS 'Идентификатор задачи для конфига прокси';


--
-- TOC entry 203 (class 1259 OID 16416)
-- Name: controllers_tasks_data; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.controllers_tasks_data
(
    controller_task_id integer NOT NULL,
    controller_data_id integer NOT NULL
);


ALTER TABLE datalake.controllers_tasks_data
    OWNER TO postgres;

--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE controllers_tasks_data; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.controllers_tasks_data IS 'Какие данные для каких задач читаем';


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN controllers_tasks_data.controller_task_id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_tasks_data.controller_task_id IS 'Идентификатор задачи';


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN controllers_tasks_data.controller_data_id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.controllers_tasks_data.controller_data_id IS 'Идентификатор данных';


--
-- TOC entry 204 (class 1259 OID 16419)
-- Name: controllers_tasks_id_seq; Type: SEQUENCE; Schema: datalake; Owner: postgres
--

CREATE SEQUENCE datalake.controllers_tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datalake.controllers_tasks_id_seq
    OWNER TO postgres;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 204
-- Name: controllers_tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: datalake; Owner: postgres
--

ALTER SEQUENCE datalake.controllers_tasks_id_seq OWNED BY datalake.controllers_tasks.id;


--
-- TOC entry 205 (class 1259 OID 16421)
-- Name: setup; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.setup
(
    setup_area_id character varying(25),
    param         character varying(25)                                                      NOT NULL,
    val           character varying(255)                                                     NOT NULL,
    cast_to       character varying(25),
    description   character varying(255) DEFAULT 'Нет описания настройки'::character varying NOT NULL
);


ALTER TABLE datalake.setup
    OWNER TO postgres;

--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE setup; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.setup IS 'Настройки';


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN setup.param; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.setup.param IS 'Параметр настройки';


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN setup.cast_to; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.setup.cast_to IS 'Преобразовать значение к типу';


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN setup.description; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.setup.description IS 'Описание настройки';


--
-- TOC entry 206 (class 1259 OID 16428)
-- Name: setup_areas; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.setup_areas
(
    id          character varying(25)  NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE datalake.setup_areas
    OWNER TO postgres;

--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE setup_areas; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.setup_areas IS 'Разделы настроек';


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 206
-- Name: COLUMN setup_areas.id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.setup_areas.id IS 'Идентификатор';


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 206
-- Name: COLUMN setup_areas.description; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.setup_areas.description IS 'Описание раздела настроек';


--
-- TOC entry 207 (class 1259 OID 16431)
-- Name: units_id_seq; Type: SEQUENCE; Schema: datalake; Owner: postgres
--

CREATE SEQUENCE datalake.units_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE datalake.units_id_seq
    OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16433)
-- Name: units; Type: TABLE; Schema: datalake; Owner: postgres
--

CREATE TABLE datalake.units
(
    id     integer                     DEFAULT nextval('datalake.units_id_seq'::regclass) NOT NULL,
    name   character varying(50)                                                          NOT NULL,
    rec_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP                          NOT NULL
);


ALTER TABLE datalake.units
    OWNER TO postgres;

--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE units; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON TABLE datalake.units IS 'Список агригатов';


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN units.id; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.units.id IS 'Идентификатор агригата';


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN units.name; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.units.name IS 'Наименование агригата';


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN units.rec_dt; Type: COMMENT; Schema: datalake; Owner: postgres
--

COMMENT ON COLUMN datalake.units.rec_dt IS 'Дата и время создания записи';


--
-- TOC entry 2850 (class 2604 OID 16442)
-- Name: controllers id; Type: DEFAULT; Schema: datalake; Owner: postgres
--

ALTER TABLE ONLY datalake.controllers
    ALTER COLUMN id SET DEFAULT nextval('datalake.controllers_id_seq1'::regclass);


--
-- TOC entry 2856 (class 2604 OID 16443)
-- Name: controllers_tasks id; Type: DEFAULT; Schema: datalake; Owner: postgres
--

ALTER TABLE ONLY datalake.controllers_tasks
    ALTER COLUMN id SET DEFAULT nextval('datalake.controllers_tasks_id_seq'::regclass);


--
-- TOC entry 2981 (class 0 OID 16396)
-- Dependencies: 198
-- Data for Name: controllers; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.controllers (id, controller_name, rack, slot, ip, write_enable, permanent_connection, timeout) FROM stdin;
1	Siemens S7 1200	0	0	172.30.143.30	f	t	5000
\.


--
-- TOC entry 2983 (class 0 OID 16405)
-- Dependencies: 200
-- Data for Name: controllers_data; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.controllers_data (id, description, unit_id, controller_id, data_block, data_type, data_offset, bitmask,
                                rec_dt, sign) FROM stdin;
1	1 байт контроллера	1	1	3	byte	0	\N	2020-03-18 09:12:14.849805	f
2	2 байт контроллера	1	1	3	byte	1	\N	2020-03-18 09:12:14.849805	f
\.


--
-- TOC entry 2985 (class 0 OID 16412)
-- Dependencies: 202
-- Data for Name: controllers_tasks; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.controllers_tasks (id, description, is_active, task_guid) FROM stdin;
1	Чтение с тестового контроллера	t	f11c0bea-68f7-11ea-9775-3863bbbc2c22
\.


--
-- TOC entry 2986 (class 0 OID 16416)
-- Dependencies: 203
-- Data for Name: controllers_tasks_data; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.controllers_tasks_data (controller_task_id, controller_data_id) FROM stdin;
1	1
1	2
\.


--
-- TOC entry 2988 (class 0 OID 16421)
-- Dependencies: 205
-- Data for Name: setup; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.setup (setup_area_id, param, val, cast_to, description) FROM stdin;
PLC_PROXY_SERVER	datablock_write_enable	0	bool	Разрешить запись в datablock?
PLC_PROXY_CLIENT	log_request	1	bool	Записывать в лог запросы к прокси серверу?
PLC_PROXY_CLIENT	log_response	1	bool	Записывать в лог ответы от proxy сервера?
\.


--
-- TOC entry 2989 (class 0 OID 16428)
-- Dependencies: 206
-- Data for Name: setup_areas; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.setup_areas (id, description) FROM stdin;
PLC_PROXY_SERVER	Настройки PLC прокси сервера
PLC_PROXY_CLIENT	Настройки PLC прокси клиента
\.


--
-- TOC entry 2991 (class 0 OID 16433)
-- Dependencies: 208
-- Data for Name: units; Type: TABLE DATA; Schema: datalake; Owner: postgres
--

COPY datalake.units (id, name, rec_dt) FROM stdin;
1	Datana Test Unit	2020-03-18 09:10:48.544994
\.


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 199
-- Name: controllers_id_seq; Type: SEQUENCE SET; Schema: datalake; Owner: postgres
--

SELECT pg_catalog.setval('datalake.controllers_id_seq', 1, false);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 201
-- Name: controllers_id_seq1; Type: SEQUENCE SET; Schema: datalake; Owner: postgres
--

SELECT pg_catalog.setval('datalake.controllers_id_seq1', 1, false);


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 204
-- Name: controllers_tasks_id_seq; Type: SEQUENCE SET; Schema: datalake; Owner: postgres
--

SELECT pg_catalog.setval('datalake.controllers_tasks_id_seq', 1, false);


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 207
-- Name: units_id_seq; Type: SEQUENCE SET; Schema: datalake; Owner: postgres
--

SELECT pg_catalog.setval('datalake.units_id_seq', 1, false);


-- Completed on 2020-03-20 11:45:13 MSK

--
-- PostgreSQL database dump complete
--

