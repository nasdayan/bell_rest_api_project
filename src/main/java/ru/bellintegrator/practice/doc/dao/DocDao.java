package ru.bellintegrator.practice.doc.dao;

import ru.bellintegrator.practice.doc.model.Doc;

import java.util.List;

/**
 * DAO документа
 */
public interface DocDao {
    /**
     * Получение списка возможных документов из справочника
     * @return список документов
     */
    List<Doc> documents();

    /**
     * Получение документа из справочника по коду документа
     * @param code код документа
     * @return документ
     */
    Doc getDocByDocCode(String code);

    /**
     * Получение документа из справочника по названию документа
     * @param name название документа
     * @return документ
     */
    Doc getDocByName(String name);
}
