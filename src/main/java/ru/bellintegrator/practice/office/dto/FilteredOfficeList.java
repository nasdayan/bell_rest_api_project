package ru.bellintegrator.practice.office.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO для получения отфильтрованного списка офисов
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilteredOfficeList {

    /**
     * Ид офиса
     */
    private Long id;

    /**
     * Название офиса
     */
    private String name;

    /**
     * Статус активности офиса
     */
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
