package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.common.SuccessResponseResult;
import ru.bellintegrator.practice.organization.dto.FilteredOrganizations;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationFilterDto;
import ru.bellintegrator.practice.organization.dto.OrganizationToSaveDto;
import ru.bellintegrator.practice.organization.service.OrganizationService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

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

    @ApiOperation(value = "Получение списка организаций", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrganizationDto.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FilteredOrganizations> organizations(@RequestBody @Valid OrganizationFilterDto organizationFilterDto) {
        return organizationService.getFilteredOrganizations(organizationFilterDto);
    }

    @ApiOperation(value = "Получение организации по id", httpMethod = "GET")
    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationDto organizationById(@PathVariable @Min(1) Long id) {
        return organizationService.getOrganizationById(id);
    }

    @ApiOperation(value = "Редактирование организации", httpMethod = "POST")
    @PostMapping(value = "/update")
    public SuccessResponseResult organizationUpdate(@RequestBody @Valid OrganizationDto organizationDto) {
        organizationService.updateOrganization(organizationDto);
        return new SuccessResponseResult();
    }

    @ApiOperation(value = "Сохранение организации", httpMethod = "POST")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)

    public SuccessResponseResult organizationSave(@RequestBody @Valid OrganizationToSaveDto organizationToSaveDto) {
        organizationService.saveOrganization(organizationToSaveDto);
        return new SuccessResponseResult();
    }
}
