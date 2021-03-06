package ru.bellintegrator.practice.country.dto;

/**
 * DTO страны
 */
public class CountryDto {
    /**
     * Название страны
     */
    private String name;

    /**
     * Код страны
     */
    private String citizenshipCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
