package ru.bellintegrator.practice.organization.model;

import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.List;

/**
 * Модель организации
 */
@Entity
@Table(name = "Organization")
public class Organization {

    /**
     * ИД организации
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название организации
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "fullName", nullable = false, length = 50)
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(name = "inn", nullable = false, length = 12)
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp", nullable = false, length = 9)
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    /**
     * Телефон организации
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Статус активности организации
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Список офисов, относящихся к организации
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Office> offices;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
