package ru.bellintegrator.practice.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO для запроса отфильтрованного списка пользователей
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserListFilterDto {

    /**
     * Ид офиса, к которому относится пользователь
     */
    @NotEmpty(message = "officeId must be not null or empty")
    private Long officeId;

    /**
     * Имя пользователя
     */
    @Size(min = 2, max = 50, message = "firstName must be more {min} and less than {max} characters")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Size(min = 2, max = 50, message = "secondName must be more {min} and less than {max} characters")
    private String secondName;

    /**
     * Отчество пользователя
     */
    @Size(min = 2, max = 50, message = "middleName must be more {min} and less than {max} characters")
    private String middleName;

    /**
     * Должность пользователя
     */
    @Size(max = 50, message = "position must be less than {max} characters")
    private String position;

    /**
     * Код документа пользователя
     */
    @Size(min = 2, max = 2, message = "docCode must be {max} characters")
    private String docCode;

    /**
     * Код страны пользователя
     */
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
