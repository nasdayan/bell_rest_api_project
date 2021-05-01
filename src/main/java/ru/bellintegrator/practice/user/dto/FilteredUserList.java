package ru.bellintegrator.practice.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO для получения отфильтрованного списка пользователей
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilteredUserList {

    /**
     * ИД пользователя
     */
    private Long id;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String secondName;

    /**
     * Отчество пользователя
     */
    private String middleName;

    /**
     * Должность пользователя
     */
    private String position;

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
}
