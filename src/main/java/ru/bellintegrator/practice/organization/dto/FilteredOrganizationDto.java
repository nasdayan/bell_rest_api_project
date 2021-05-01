package ru.bellintegrator.practice.organization.dto;

/**
 * DTO для получения отфильтрованного списка организаций
 */
public class FilteredOrganizationDto {

    /**
     * ИД организации
     */
    private Long id;

    /**
     * Название организации
     */
    private String name;

    /**
     * Статус активности организации
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
