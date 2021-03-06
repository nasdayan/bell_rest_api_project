package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.office.dto.FilteredOfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeListFilterDto;
import ru.bellintegrator.practice.office.dto.OfficeToSaveDto;
import ru.bellintegrator.practice.office.service.OfficeService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Контроллер для управления информацией об офисах
 */
@Api(value = "OfficeController", description = "Управление информацией об офисах")
@Validated
@RestController
@RequestMapping(value = "/api/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получение списка офисов
     * @param officeListFilterDto фильтр
     * @return список офисов
     */
    @ApiOperation(value = "Получение списка офисов", httpMethod = "POST")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FilteredOfficeDto> officeList(@RequestBody @Valid OfficeListFilterDto officeListFilterDto) {
        return officeService.getFilteredOfficeList(officeListFilterDto);
    }

    /**
     * Получение офиса по id
     * @param id ИД офиса
     * @return офис
     */
    @ApiOperation(value = "Получение офиса по id", httpMethod = "GET")
    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OfficeDto officeById(@PathVariable @Min(1) Long id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Редактирование офиса
     * @param officeDto офис, который треб-ся отредактировать
     */
    @ApiOperation(value = "Редактирование офиса", httpMethod = "POST")
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void officeUpdate(@RequestBody @Valid OfficeDto officeDto) {
        officeService.updateOffice(officeDto);
    }

    /**
     * Сохранение офиса
     * @param officeToSaveDto офис, который треб-ся сохранить
     */
    @ApiOperation(value = "Сохранение офиса", httpMethod = "POST")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public void officeSave(@RequestBody @Valid OfficeToSaveDto officeToSaveDto) {
        officeService.saveOffice(officeToSaveDto);
    }
}
