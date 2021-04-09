package ru.bellintegrator.practice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager entityManager;

    @Autowired
    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Office getOfficeById(Long id) {
        return entityManager.find(Office.class, id);
    }

    @Override
    public List<Office> getOfficeFilteredList(Office filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        Predicate predicate = criteriaBuilder.equal(officeRoot.get("organization").get("id"), filter.getOrganization().getId());
        if (predicate == null) {
            throw new ItemNotFoundException("Organization with this identifier was not found");
        }
        if (filter.getName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("name"), filter.getName()));
        }
        if (filter.getPhone() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("phone"), filter.getPhone()));
        }
        if (filter.getIsActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("isActive"), filter.getIsActive()));
        }
        criteriaQuery.select(officeRoot).where(predicate);
        TypedQuery<Office> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


    @Override
    public void updateOffice(Office office) {
        Long id = office.getId();
        Office officeDao = entityManager.find(Office.class, id);
        officeDao.setId(office.getId());
        officeDao.setName(office.getName());
        officeDao.setAddress(office.getAddress());
        officeDao.setPhone(office.getPhone());
        officeDao.setIsActive(office.getIsActive());
    }

    @Override
    public void saveOffice(Office office) {
        entityManager.persist(office);
    }
}
