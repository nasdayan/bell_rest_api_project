package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

/**
 * DAO офиса
 */
public interface OfficeDao {

    /**
     * Получение офиса по Ид
     * @param id Ид офиса
     * @return офис
     */
    Office getOfficeById(Long id);

    /**
     * Получение отфильрованного списка офисов
     * @param filter фильтр
     * @return список офисов
     */
    List<Office> getOfficeFilteredList(Office filter);

    /**
     * Обновление офиса
     * @param office офис
     */
    void updateOffice(Office office);

    /**
     * Сохранение офиса
     * @param office офис
     */
    void saveOffice(Office office);
}
