package ru.bellintegrator.practice.office.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Офис для сохранения")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeToSaveDto {

    @NotEmpty(message = "name cannot be null or empty")
    @Size(min = 2, max = 100, message = "name must be more {min} and less than {max} characters")
    private String name;

    @NotEmpty(message = "address cannot be null or empty")
    @Size(min = 2, max = 100, message = "address must be more {min} and less than {max} characters")
    private String address;

    @Size(max = 50, message = "phone must be less than {max} characters")
    private String phone;

    private Boolean isActive;

    @NotNull(message = "orgId cannot be null")
    private Long orgId;

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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
