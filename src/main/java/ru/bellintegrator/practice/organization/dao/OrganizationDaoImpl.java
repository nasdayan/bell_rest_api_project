package ru.bellintegrator.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager entityManager;

    @Autowired
    public OrganizationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Organization> getFilteredOrganizationList(Organization filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        Predicate predicate = criteriaBuilder.equal(organizationRoot.get("name"), filter.getName());
        if (predicate == null) {
            throw new ItemNotFoundException("Organizations with this name were not found");
        }
        if (filter.getInn() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("inn"), filter.getInn()));
        }
        if (filter.getIsActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("isActive"), filter.getIsActive()));
        }
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Organization getOrganizationById(Long id) {
        return entityManager.find(Organization.class, id);
    }

    @Override
    public void updateOrganization(Organization organization) {

        Long id = organization.getId();
        Organization organizationDao =  entityManager.find(Organization.class, id);
        organizationDao.setId(organization.getId());
        organizationDao.setName(organization.getName());
        organizationDao.setFullName(organization.getFullName());
        organizationDao.setInn(organization.getInn());
        organizationDao.setKpp(organization.getKpp());
        organizationDao.setAddress(organization.getAddress());
        organizationDao.setPhone(organization.getPhone());
        organizationDao.setIsActive(organization.getIsActive());
    }

    @Override
    public void saveOrganization(Organization organization) {
        entityManager.persist(organization);
    }
}
