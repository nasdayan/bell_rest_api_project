package ru.bellintegrator.practice.doc.dao;

import ru.bellintegrator.practice.doc.model.Doc;

import java.util.List;

public interface DocDao {
    /**
     * Получение списка возможных документов из справочника
     * @return список документов
     */
    List<Doc> documents();
}
