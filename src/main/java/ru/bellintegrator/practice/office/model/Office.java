package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.List;

/**
 * Модель офиса
 */
@Entity
@Table(name = "Office")
public class Office {

    /**
     * ИД офиса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название офиса
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Телефон офиса
     */
    @Column(name = "phone", length = 50)
    private String phone;

    /**
     * Адрес офиса
     */
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    /**
     * Статус активности офиса
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Организация, к которой относится офис
     */
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    /**
     * Список пользователей, которые относятся к офису
     */
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "office_id")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
