package ru.bellintegrator.practice.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserByIdDto {

    @NotNull
    @ApiModelProperty(value = "Уникальный идентификатор")
    private Long id;

    @NotEmpty(message = "firstName must be not null or empty")
    @Size(min = 2, max = 50, message = "firstName must be more {min} and less than {max} characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "secondName must be more {min} and less than {max} characters")
    private String secondName;

    @Size(min = 2, max = 50, message = "middleName must be more {min} and less than {max} characters")
    private String middleName;

    @NotEmpty(message = "position must be not null or empty")
    @Size(max = 50, message = "position must be less than {max} characters")
    private String position;

    @Size(max = 20, message = "phone must be less than {max} characters")
    private String phone;

    @Size(max = 50, message = "docName must be less than {max} characters")
    private String docName;

    @Size(max = 50, message = "docNumber must be less than {max} characters")
    private String docNumber;

    private Date docDate;

    @Size(max = 50, message = "citizenshipName must be less than {max} characters")
    private String citizenshipName;

    @Size(min = 3, max = 3, message = "citizenshipCode must be {max} characters")
    private String citizenshipCode;

    private Boolean isIdentified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }
}

