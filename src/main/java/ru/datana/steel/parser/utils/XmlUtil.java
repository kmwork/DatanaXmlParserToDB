package ru.datana.steel.parser.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

@Slf4j
public class XmlUtil {

    private static final boolean XML_FORMATTED = true;


    public static String xmlElementToText(Object xMain) throws AppException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(xMain.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, XML_FORMATTED);
            StringWriter sw = new StringWriter(16 * 1024);
            jaxbMarshaller.marshal(xMain, sw);
            return sw.getBuffer().toString();
        } catch (Exception e) {
            String msg = "Ошибка формарования XML для класса " + xMain.getClass();
            String args = "xMain = " + xMain;
            throw new AppException(TypeException.XML_TO_TEXT_ERROR, msg, args, e);
        }
    }


    public static <XML_CLASS> XML_CLASS xmlFileToObject(File xmlFile, Class<XML_CLASS> clazz) throws AppException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object result = unmarshaller.unmarshal(xmlFile);
            return (XML_CLASS) result;
        } catch (Exception e) {
            String msg = "Ошибка парсинга XML для класса " + clazz.getCanonicalName();
            String args = "xmlFile = " + xmlFile.getAbsolutePath() + ", clazz = " + clazz.getName();
            throw new AppException(TypeException.XML_TO_TEXT_ERROR, msg, args, e);
        }

    }

}