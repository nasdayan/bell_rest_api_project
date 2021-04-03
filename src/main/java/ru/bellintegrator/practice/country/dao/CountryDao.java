package ru.bellintegrator.practice.country.dao;

import ru.bellintegrator.practice.country.model.Country;

import java.util.List;

public interface CountryDao {
    /**
     * Получение списка стран
     * @return список стран
     */
    List<Country> countries();

    /**
     * Получие страны по коду
     * @param code код страны
     * @return страна
     */
    Country getCountryByCitizenshipCode(String code);
}
