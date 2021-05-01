package ru.bellintegrator.practice.country.service;

import ru.bellintegrator.practice.country.dto.CountryDto;

import java.util.List;

/**
 * Сервис для сущности Страна
 */
public interface CountryService {
    /**
     * Получение списка стран
     * @return список стран
     */
    List<CountryDto> countries();
}
