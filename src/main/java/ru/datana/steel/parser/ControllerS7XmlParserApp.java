package ru.datana.steel.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControllerS7XmlParserApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(S7AppRunner.class, args);
    }
}

