package ru.datana.steel.parser.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Slf4j
public class XmlUtil {

    private static final String PREFIX_LOG = "[XML] ";

    public static <XML_CLASS> XML_CLASS xmlFileToObject(File xmlFile, Class<XML_CLASS> clazz) throws AppException {
        try {
            log.info(PREFIX_LOG + " Начало разбора файла " + xmlFile.getAbsolutePath() + " для класса " + clazz.getSimpleName());
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object result = unmarshaller.unmarshal(xmlFile);
            log.info("Файл разобран как объект класса " + result.getClass().getSimpleName() + ": " + result.toString());
            return (XML_CLASS) result;
        } catch (Exception e) {
            String msg = "Ошибка парсинга XML для класса " + clazz.getCanonicalName();
            String args = "xmlFile = " + xmlFile.getAbsolutePath() + ", clazz = " + clazz.getSimpleName();
            throw new AppException(TypeException.XML_TO_TEXT_ERROR, msg, args, e);
        }

    }

}