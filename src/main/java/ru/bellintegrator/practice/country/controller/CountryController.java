package ru.bellintegrator.practice.country.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.country.dto.CountryDto;
import ru.bellintegrator.practice.country.service.CountryService;

import java.util.List;

/**
 * Контроллер для управление информацией о странах
 */
@Api(value = "CountryController", description = "Управление информацией о странах")
@RestController
@RequestMapping(value = "/api")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Получение справочника стран
     * @return список стран
     */
    @ApiOperation(value = "Получение справочника стран", httpMethod = "POST")
    @PostMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryDto> countries() {
        return countryService.countries();
    }
}
