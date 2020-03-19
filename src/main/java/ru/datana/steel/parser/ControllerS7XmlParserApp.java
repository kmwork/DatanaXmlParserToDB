package ru.datana.steel.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.datana.steel.parser.config.AppConst;
import ru.datana.steel.parser.config.LanitSpringConfig;
import ru.datana.steel.parser.jpa.SaveToDBService;
import ru.datana.steel.parser.model.LanitEntryBuilder;
import ru.datana.steel.parser.model.xml.ControllerType;
import ru.datana.steel.parser.model.xml.ItemsType;
import ru.datana.steel.parser.model.xml.RootType;
import ru.datana.steel.parser.utils.AppException;
import ru.datana.steel.parser.utils.TypeException;
import ru.datana.steel.parser.utils.XmlUtil;

import java.io.File;
import java.util.*;

@Slf4j
@SpringBootApplication
@Import(LanitSpringConfig.class)
public class ControllerS7XmlParserApp implements CommandLineRunner {

    @Autowired
    private LanitSpringConfig lanitSpringConfig;

    @Autowired
    SaveToDBService saveToDBService;

    public static void main(String[] args) throws Exception {
        extConfigure();
        SpringApplication app = new SpringApplication(ControllerS7XmlParserApp.class);
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


    @Override
    public void run(String... args) throws Exception {
        log.info(AppConst.APP_LOG_PREFIX + "================ Запуск  ================. Аргументы = " + Arrays.toString(args));
        try {
            log.info(AppConst.APP_LOG_PREFIX + "Версия XML Парсера для ММК: " + lanitSpringConfig.getAppVersion());

            XmlUtil xmlUtil = XmlUtil.getInstance();
            File xmlS7RootFile = new File(lanitSpringConfig.getDataDir(), AppConst.S7_ROOT_CONFIG_FILE_NAME);
            RootType rootConfig = xmlUtil.xmlFileToObject(xmlS7RootFile, RootType.class);
            LanitEntryBuilder builder = new LanitEntryBuilder();
            List<ControllerType> controllerTypeList = rootConfig.getControllers().getController();
            builder.addControllerList(controllerTypeList);


            Set<String> nodes = new LinkedHashSet<>(controllerTypeList.size());
            Map<String, Set<String>> nameByNode = new HashMap<>(controllerTypeList.size());
            for (ControllerType controllerType : controllerTypeList) {
                String node = controllerType.getNote().toUpperCase().trim();
                String nameController = controllerType.getName().toUpperCase().trim();

                if (StringUtils.isEmpty(nameController) || StringUtils.isEmpty(node)) {
                    String strArgs = "node: '" + node + "', nameController = '" + nameController + "'";
                    throw new AppException(TypeException.ROOT_XML_NULL_ATTRIBUTE_LINES_ERROR, "Пустое поле [node] или [name]", strArgs, null);
                }

                nodes.add(node);
                Set<String> names = nameByNode.get(node);
                if (names == null) {
                    names = new TreeSet<>();
                    nameByNode.put(node, names);
                }

                if (names.contains(nameController)) {
                    String strArgs = "root file: " + xmlS7RootFile.getAbsolutePath() + " node: " + node + ", names:" + names + " для nameController = " + nameController;
                    throw new AppException(TypeException.ROOT_XML_DUPLICATE_LINES_ERROR, "Дублирование пара [node][name]", strArgs, null);
                }


                names.add(nameController);
            }
            log.info(AppConst.APP_LOG_PREFIX + " Найдено нод: " + nodes.toString());

            Map<String, ItemsType> controllerByNode = new HashMap<>(nodes.size());
            for (String node : nodes) {
                StringBuilder dir = new StringBuilder(1024);
                dir.append(lanitSpringConfig.getDataDir());
                dir.append(File.separator);
                dir.append(node);
                dir.append(File.separator);
                Set<String> names = nameByNode.get(node);
                if (names == null || names.size() == 0) {
                    continue;
                }

                File fileDir = new File(dir.toString());
                if (!fileDir.exists()) {
                    String strArgs = "dir file: " + fileDir.getAbsoluteFile() + ", node: " + node + ", names:" + names;
                    throw new AppException(TypeException.DIR_NOT_FOUND, "Нет папки для скана с нодой котроллера", strArgs, null);
                }
                for (String nameController : names) {
                    parseSingleController(builder, node, dir.toString(), nameController);
                }
            }

            log.info(AppConst.SUCCESS_LOG_PREFIX + "Обработано без ошибок " + xmlUtil.getSuccessCount() + " файлов");

            if (xmlUtil.getFailCount() > 0)
                log.error(AppConst.ERROR_LOG_PREFIX + "Найдено ошибок в " + xmlUtil.getFailCount() + " файлов");
            else {
                saveToDBService.saveRecords(builder);
            }
        } catch (Exception ex) {
            log.error(AppConst.ERROR_LOG_PREFIX + " Ошибка в программе", ex);
        }
        log.info(AppConst.APP_LOG_PREFIX + "********* Завершение программы *********");
    }


    private void parseSingleController(LanitEntryBuilder builder, String node, String dir, String nameController) throws AppException {
        XmlUtil xmlUtil = XmlUtil.getInstance();
        File xmlS7DbFileDir = new File(dir + File.separator + nameController + File.separator);


        if (!xmlS7DbFileDir.exists()) {
            String strArgs = "dir file: " + xmlS7DbFileDir.getAbsoluteFile() + " node: " + node + ", nameController:" + nameController;
            throw new AppException(TypeException.DIR_NOT_FOUND, "Нет папки для скана db-файлов", strArgs, null);
        }

        String[] dbFile = xmlS7DbFileDir.list();
        List<File> selectedDBFiles = new ArrayList<>(dbFile.length);
        for (String fileName : dbFile) {
            File f = new File(xmlS7DbFileDir, fileName);

            if (f.isDirectory() || !f.getName().matches(AppConst.S7_REG_EXPRESSION_DB_NAME)) {
                log.warn(AppConst.ERROR_LOG_PREFIX + "Пропущен файл (или каталог)" + f.getAbsoluteFile());
                continue;
            }

            selectedDBFiles.add(f);
        }

        for (File f : selectedDBFiles) {
            ItemsType items = xmlUtil.xmlFileToObject(f, ItemsType.class);
            builder.addItems(node, nameController, f.getName(), items);
        }
    }

}

