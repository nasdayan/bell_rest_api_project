package ru.bellintegrator.practice.doc.service;

import ru.bellintegrator.practice.doc.dto.DocDto;

import java.util.List;

/**
 * Сервис документа
 */
public interface DocService {

    /**
     * Получение списка документов
     * @return список документов
     */
    List<DocDto> documents();
}
