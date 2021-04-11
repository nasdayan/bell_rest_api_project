package ru.bellintegrator.practice.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserToSaveDto {

    @NotNull(message = "officeId must be not null")
    private Long officeId;

    @NotEmpty(message = "firstName must be not null or empty")
    @Size(min = 2, max = 50, message = "The field must be more {min} and less than {max} characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "The field must be more {min} and less than {max} characters")
    private String secondName;

    @Size(min = 2, max = 50, message = "The field must be more {min} and less than {max} characters")
    private String middleName;

    @NotEmpty(message = "position must be not null or empty")
    @Size(max = 50, message = "The field must be less than {max} characters")
    private String position;

    @Size(max = 20, message = "The field must be less than {max} characters")
    private String phone;

    @Size(min = 2, max = 2, message = "The field must be more {min} and less than {max} characters")
    private String docCode;

    private String docName;

    private String docNumber;

    private Date docDate;

    @Size(min = 3, max = 3, message = "The field's length must be {max}")
    private String citizenshipCode;

    private Boolean isIdentified;

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

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
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
}
