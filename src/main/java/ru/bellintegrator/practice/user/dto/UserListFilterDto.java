package ru.bellintegrator.practice.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserListFilterDto {

    @NotEmpty(message = "officeId must be not null or empty")
    private Long officeId;

    @Size(min = 2, max = 50, message = "firstName must be more {min} and less than {max} characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "secondName must be more {min} and less than {max} characters")
    private String secondName;

    @Size(min = 2, max = 50, message = "middleName must be more {min} and less than {max} characters")
    private String middleName;

    @Size(max = 50, message = "position must be less than {max} characters")
    private String position;

    @Size(min = 2, max = 2, message = "docCode must be {max} characters")
    private String docCode;

    @Size(min = 3, max = 3, message = "citizenshipCode must be {max} characters")
    private String citizenshipCode;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
