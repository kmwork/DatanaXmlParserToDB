package ru.datana.steel.parser.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Slf4j
public class XmlUtil {

    private static final String PREFIX_LOG = "[XML] ";

    @Getter
    private int successCount = 0;

    @Getter
    private int failCount = 0;

    public <XML_CLASS> XML_CLASS xmlFileToObject(File xmlFile, Class<XML_CLASS> clazz) throws AppException {
        try {
            log.info(PREFIX_LOG + " Начало разбора файла " + xmlFile.getAbsolutePath() + " для класса " + clazz.getSimpleName());
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XML_CLASS result = (XML_CLASS) unmarshaller.unmarshal(xmlFile);
            log.info(PREFIX_LOG + "Файл разобран как объект класса " + result.getClass().getSimpleName());
            log.trace(PREFIX_LOG + "result = " + result.toString());

            successCount++;
            return result;
        } catch (Exception e) {
            failCount++;
            String msg = "Ошибка парсинга XML для класса " + clazz.getCanonicalName();
            String args = "xmlFile = " + xmlFile.getAbsolutePath() + ", clazz = " + clazz.getSimpleName();
            throw new AppException(TypeException.XML_TO_TEXT_ERROR, msg, args, e);
        }
    }

}