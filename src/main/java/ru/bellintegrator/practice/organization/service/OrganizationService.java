package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.dto.FilteredOrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationFilterDto;
import ru.bellintegrator.practice.organization.dto.OrganizationToSaveDto;

import java.util.List;

/**
 * Сервис организации
 */
public interface OrganizationService {
    /**
     * Получение списка организаций
     * @return список организаций
     * @param organizationFilterDto фильтр организаций
     */
    List<FilteredOrganizationDto> getFilteredOrganizations(OrganizationFilterDto organizationFilterDto);

    /**
     * Получение организации по ИД
     * @return организация
     */
    OrganizationDto getOrganizationById(Long id);

    /**
     * Редактирование организации
     * @param organizationDto организация для отредактирования
     */
    void updateOrganization(OrganizationDto organizationDto);

    /**
     * Сохранение организации
     * @param organizationToSaveDto организация для сохранения
     */
    void saveOrganization(OrganizationToSaveDto organizationToSaveDto);
}