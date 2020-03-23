#!/bin/sh
export JAVA_HOME=/home/lin/apps/jdk13
export PATH=$JAVA_HOME/bin:$PATH
java java -Dapp.profile="remote_postgres" -Dapp.dir="/home/lin/apps/datana_xml_parser" -Dfile.encoding="UTF8" -jar S7ParserXML-K6-1-alfa-spring-boot.jar
