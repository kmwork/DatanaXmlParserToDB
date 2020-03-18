package ru.datana.steel.parser.utils;

import lombok.Getter;
import lombok.ToString;

/**
 * Вид Exception для внешеного потребителея
 */
@ToString
@Getter
public enum TypeException {

    OK(0, "Успешно"),
    SYSTEM_ERROR(-1, "Системная ошибка"),
    INVALID_USER_INPUT_DATA(-2, "Не корректные введенные данные"),
    XML_TO_TEXT_ERROR(-3, "Не верный входной XML");
    private final int codeError;
    private final String descError;

    TypeException(int codeError, String descError) {
        this.codeError = codeError;
        this.descError = descError;
    }
}
