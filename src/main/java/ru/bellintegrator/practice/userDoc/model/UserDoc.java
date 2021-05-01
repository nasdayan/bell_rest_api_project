package ru.bellintegrator.practice.userDoc.model;

import ru.bellintegrator.practice.doc.model.Doc;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;

/**
 * Модель документа пользователя
 */
@Entity
@Table(name = "user_doc")
public class UserDoc {

    /**
     * ИД документа пользователя
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
     * Номер документа пользователя
     */
    @Column(name = "doc_number")
    private String docNumber;

    /**
     * Дата выдачи документа пользователя
     */
    @Column(name = "doc_date")
    private Date docDate;

    /**
     * Пользователь, который относится к документу пользователя
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userDoc")
    private User user;

    /**
     * Документ (из справочника документов), относящийся к документу пользователя
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }
}
