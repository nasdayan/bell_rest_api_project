package ru.bellintegrator.practice.organization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO для запроса списка организаций по фильтру
 */
@ApiModel(description = "Фильтр для поиска организаций")
public class OrganizationFilterDto {

    /**
     * Название организации
     */
    @NotEmpty(message = "name must be not null or empty")
    @Size(min = 2, max = 50, message = "name must be more {min} and less than {max} characters")
    private String name;

    /**
     * ИНН организации
     */
    @Size(min = 12, max = 12, message = "inn must be {max} characters")
    private String inn;

    /**
     * Статус активности организации
     */
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @JsonProperty(value = "isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
