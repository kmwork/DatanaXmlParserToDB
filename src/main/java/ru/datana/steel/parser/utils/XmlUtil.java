package ru.datana.steel.parser.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.datana.steel.parser.config.AppConst;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;

@Slf4j
public class XmlUtil {

    private static final String PREFIX_LOG = "[XML] ";

    @Getter
    private int successCount = 0;

    @Getter
    private int failCount = 0;

    public <XML_CLASS> XML_CLASS xmlFileToObject(File xmlFile, Class<XML_CLASS> clazz) throws AppException {
        try (FileReader fileReader = new FileReader(xmlFile, Charset.forName(AppConst.ENCODING))) {
            log.info(PREFIX_LOG + " Начало разбора файла " + xmlFile.getAbsolutePath() + " для класса " + clazz.getSimpleName());
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStream = factory.createXMLStreamReader(fileReader);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<XML_CLASS> jaxbElement = unmarshaller.unmarshal(xmlStream, clazz);
            XML_CLASS result = jaxbElement.getValue();
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