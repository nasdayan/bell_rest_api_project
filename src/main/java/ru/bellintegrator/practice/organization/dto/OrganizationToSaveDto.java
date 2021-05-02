package ru.bellintegrator.practice.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO для сохранения организации
 */
@ApiModel(description = "Организация для сохранения")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationToSaveDto {

    /**
     * Название организации
     */
    @NotEmpty(message = "name must be not null or empty")
    @Size(min = 2, max = 50, message = "The field must be more {min} and less than {max} characters")
    private String name;

    /**
     * Полное название организации
     */
    @NotEmpty(message = "fullName must be not null or empty")
    @Size(min = 2, max = 50, message = "fullName must be more {min} and less than {max} characters")
    private String fullName;

    /**
     * ИНН организации
     */
    @NotEmpty(message = "inn must be not null or empty")
    @Size(min = 12, max = 12, message = "inn must be {max} characters")
    private String inn;

    /**
     * КПП организации
     */
    @NotEmpty(message = "kpp must be not null or empty")
    @Size(min = 9, max = 9, message = "kpp must be {max} characters")
    private String kpp;

    /**
     * Адрес организации
     */
    @NotEmpty(message = "address must be not null or empty")
    @Size(max = 100, message = "address must be less than {max} characters")
    private String address;

    /**
     * Телефон организации
     */
    @Size(max = 20, message = "phone must be less than {max} characters")
    private String phone;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
