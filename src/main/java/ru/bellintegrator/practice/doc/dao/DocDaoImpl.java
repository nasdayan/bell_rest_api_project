package ru.bellintegrator.practice.doc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.doc.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager entityManager;

    @Autowired
    public DocDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Doc> documents() {
        TypedQuery<Doc> query = entityManager.createQuery("SELECT e FROM Doc e", Doc.class);
        return query.getResultList();
    }

    @Override
    public Doc getDocByDocCode(String code) {
        String queryString = "SELECT d FROM " + Doc.class.getSimpleName() + " d WHERE d.code = :code";
        TypedQuery<Doc> query = entityManager.createQuery(queryString, Doc.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    @Override
    public Doc getDocByName(String name) {
        String queryString = "SELECT d FROM " + Doc.class.getSimpleName() + " d WHERE d.name = :name";
        TypedQuery<Doc> query = entityManager.createQuery(queryString, Doc.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
