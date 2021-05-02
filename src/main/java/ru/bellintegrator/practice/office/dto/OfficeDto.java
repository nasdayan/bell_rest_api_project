package ru.bellintegrator.practice.office.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO офиса
 */
@ApiModel(description = "Офис")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeDto {

    /**
     * Ид офиса
     */
    @NotNull
    @ApiModelProperty(value = "Уникальный идентификатор")
    private Long id;

    /**
     * Название офиса
     */
    @NotEmpty(message = "name must be not null or empty")
    @Size(min = 2, max = 100, message = "name must be more {min} and less than {max} characters")
    private String name;

    /**
     * Адрес офиса
     */
    @NotEmpty(message = "address must be not null or empty")
    @Size(min = 2, max = 100, message = "address must be more {min} and less than {max} characters")
    private String address;

    /**
     * Телефон офиса
     */
    @Size(max = 50, message = "phone must be less than {max} characters")
    private String phone;

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
