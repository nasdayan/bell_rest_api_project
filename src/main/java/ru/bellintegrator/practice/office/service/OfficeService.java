package ru.bellintegrator.practice.office.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.dto.FilteredOfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeListFilterDto;
import ru.bellintegrator.practice.office.dto.OfficeToSaveDto;

import java.util.List;

/**
 * Сервис офиса
 */
@Service
public interface OfficeService {

    /**
     * Получение списка офисов по фильтру
     * @param officeListFilterDto фильтр
     * @return список офисов
     */
    List<FilteredOfficeDto> getFilteredOfficeList(OfficeListFilterDto officeListFilterDto);

    /**
     * Получение офиса по ID
     * @param id ID офиса
     * @return офис
     */
    OfficeDto getOfficeById(Long id);

    /**
     * Редактирование офиса
     * @param officeDto Параметры офиса
     */
    void updateOffice(OfficeDto officeDto);

    /**
     * Сохранение офиса
     * @param officeToSaveDto Параметры офиса
     */
    void saveOffice(OfficeToSaveDto officeToSaveDto);
}
