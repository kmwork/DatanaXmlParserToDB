# Загручик файлов настроек завода ММК в базу данных Postgres (типа как для BigData)
для контролера Siemens S7 1200 (https://new.siemens.com/ru/ru/produkty/avtomatizacia/sistemy-avtomatizacii/promyshlennye-sistemy-simatic/kontroller-simatic/s7-1200.html)

## cборка Maven 3 + JDK 13
`mvn clean compile assembly:single`

## Как пользоваться
после сборки мавеном, из папки <this project>/etc для bat и sh командые файлы

### примерный синтаксис команды
`java -Dapp.dir=<путь к папке config> -jar target/S7ParserXML-K6-1-alfa-jar-with-dependencies.jar`
путь к папке config - где файл `datana_xml_parser.properties` c настройками
## настройка
файл `datana_xml_parser.properties` c настройками + папка DATA с XML

## техническая документация 
в папке <this project>/doc-manual