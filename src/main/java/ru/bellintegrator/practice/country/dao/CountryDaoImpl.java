package ru.bellintegrator.practice.country.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager entityManager;

    @Autowired
    public CountryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Country> countries() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    @Override
    public Country getCountryByCitizenshipCode(String code) {
        String queryString = "SELECT c FROM " + Country.class.getSimpleName() + " c WHERE c.citizenshipCode = :code";
        TypedQuery<Country> query = entityManager.createQuery(queryString, Country.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

}
