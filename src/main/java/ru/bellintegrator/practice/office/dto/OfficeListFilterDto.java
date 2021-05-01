package ru.bellintegrator.practice.office.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для запроса списка офисов по фильтру
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeListFilterDto {

    /**
     * ИД организации
     */
    @NotNull(message = "orgId cannot be null")
    private Long orgId;

    /**
     * Название офиса
     */
    @Size(min = 2, max = 100, message = "name must be more {min} and less than {max} characters")
    private String name;

    /**
     * Телефон офиса
     */
    @Size(max = 50, message = "phone must be less than {max} characters")
    private String phone;

    /**
     * Статус активности офиса
     */
    private Boolean isActive;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
