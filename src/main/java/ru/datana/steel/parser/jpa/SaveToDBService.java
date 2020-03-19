package ru.datana.steel.parser.jpa;

import ru.datana.steel.parser.model.LanitEntryBuilder;

public interface SaveToDBService {
    void saveRecords(LanitEntryBuilder builder);

    void dropRecords();
}
