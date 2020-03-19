# Загрузчик файлов настроек завода ММК в базу данных Postgres (типа как для BigData)
#### кратко: Загрузка конфигурации контроллеров из xml

### Тех задание - https://conf.dds.lanit.ru/pages/viewpage.action?pageId=43683818
(или файл -NIOKR-43683818-180320-1517-61.pdf в папке <this project>/doc-manual)
#### Задача в JIRA - https://jira.dds.lanit.ru/browse/VACUM-22

### Конечная цель - https://conf.dds.lanit.ru/display/NIOKR/PLC+Proxy+Server 
(или файл - NIOKR-PLCProxyServer-180320-1342-59.pdf в папке <this project>/doc-manual)
## cборка Maven 3 + JDK 13
`mvn clean compile package`
## Как пользоваться
после сборки мавеном, из папки <this project>/etc для bat и sh командые файлы
### примерный синтаксис команды
`java -Dapp.dir=<путь к папке config> -jar target/S7ParserXML-K6-1-alfa-jar-with-dependencies.jar`
путь к папке config - где файл `datana_xml_parser.properties` c настройками
## настройка
файл `datana_xml_parser.properties` c настройками + папка DATA с XML
## техническая документация 
в папке <this project>/doc-manual
