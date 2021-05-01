package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

/**
 * DAO организации
 */
public interface OrganizationDao {

    /**
     * Получение отфильтрованного списка организаций
     * @param filter фильтр
     * @return список организаций
     */
    List<Organization> getFilteredOrganizationList(Organization filter);

    /**
     * Получение организации по Ид
     * @param id Ид организации
     * @return Организация
     */
    Organization getOrganizationById(Long id);

    /**
     * Обновление организации
     * @param organization организация
     */
    void updateOrganization(Organization organization);

    /**
     * Сохранение организации
     * @param organization Организация
     */
    void saveOrganization(Organization organization);
}
