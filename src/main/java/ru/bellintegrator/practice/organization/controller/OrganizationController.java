package ru.bellintegrator.practice.organization.controller;

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
import ru.bellintegrator.practice.organization.dto.FilteredOrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationFilterDto;
import ru.bellintegrator.practice.organization.dto.OrganizationToSaveDto;
import ru.bellintegrator.practice.organization.service.OrganizationService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Контроллер для уравления информацией об организации
 */
@Api(value = "OrganizationController", description = "Управление информацией об организации")
@Validated
@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Получение списка организаций
     * @param organizationFilterDto фильтр
     * @return список организаций
     */
    @ApiOperation(value = "Получение списка организаций", httpMethod = "POST")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FilteredOrganizationDto> organizations(@RequestBody @Valid OrganizationFilterDto organizationFilterDto) {
        return organizationService.getFilteredOrganizations(organizationFilterDto);
    }

    /**
     * Получение организации по id
     * @param id ИД организации
     * @return организация
     */
    @ApiOperation(value = "Получение организации по id", httpMethod = "GET")
    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationDto organizationById(@PathVariable @Min(1) Long id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * Редактирование организации
     * @param organizationDto организация, которую треб-ся отредактировать
     */
    @ApiOperation(value = "Редактирование организации", httpMethod = "POST")
    @PostMapping(value = "/update")
    public void organizationUpdate(@RequestBody @Valid OrganizationDto organizationDto) {
        organizationService.updateOrganization(organizationDto);
    }

    /**
     * Сохранение организации
     * @param organizationToSaveDto организация, которую треб-ся сохранить
     */
    @ApiOperation(value = "Сохранение организации", httpMethod = "POST")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)

    public void organizationSave(@RequestBody @Valid OrganizationToSaveDto organizationToSaveDto) {
        organizationService.saveOrganization(organizationToSaveDto);
    }
}
