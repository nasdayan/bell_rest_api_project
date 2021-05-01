package ru.bellintegrator.practice.doc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.doc.dto.DocDto;
import ru.bellintegrator.practice.doc.service.DocService;

import java.util.List;

/**
 * Контроллер для управления информацией о документах
 */
@Api(value = "DocController", description = "Управление информацией о документах")
@RestController
@RequestMapping(value = "/api")
public class DocController {

    private final DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    /**
     * Получение справочника документов
     * @return список документов
     */
    @ApiOperation(value = "Получение справочника документов", httpMethod = "POST")
    @PostMapping(value = "/docs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DocDto> documents() {
        return docService.documents();
    }
}
