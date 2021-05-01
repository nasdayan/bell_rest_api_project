package ru.bellintegrator.practice.country.model;

import ru.bellintegrator.practice.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Модель страны
 */
@Entity
@Table(name = "Country")
public class Country {

    /**
     * Ид страны
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Код страны
     */
    @Column(name = "citizenship_code", length = 3, nullable = false)
    private String citizenshipCode;

    /**
     * Название страны
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Список пользователей, относящихся ко стране
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "country")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
