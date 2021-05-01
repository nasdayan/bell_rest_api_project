package ru.bellintegrator.practice.doc.model;

import ru.bellintegrator.practice.userDoc.model.UserDoc;

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
 * Модель документа
 */
@Entity
@Table(name = "Doc")
public class Doc {

    /**
     * Ид документа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Название документа
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "code", length = 2, nullable = false)
    private String code;

    /**
     * Список документов пользователя, относящихся к справочнику документов
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doc")
    private List<UserDoc> userDocs;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserDoc> getUserDocs() {
        return userDocs;
    }

    public void setUserDocs(List<UserDoc> userDocs) {
        this.userDocs = userDocs;
    }
}
