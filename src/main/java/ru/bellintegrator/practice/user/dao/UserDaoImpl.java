package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getFilteredUserList(User filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.equal(userRoot.get("office").get("id"), filter.getOffice().getId());
        if (predicate == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        if (filter.getFirstName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("firstName"), filter.getFirstName()));
        }
        if (filter.getSecondName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("secondName"), filter.getSecondName()));
        }
        if (filter.getMiddleName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("middleName"), filter.getMiddleName()));
        }
        if (filter.getPosition() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("position"), filter.getPosition()));
        }
        if (filter.getUserDoc() != null && filter.getUserDoc().getDoc() != null && filter.getUserDoc().getDoc().getCode() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("userDoc").get("doc").get("code"), filter.getUserDoc().getDoc().getCode()));
        }
        if (filter.getCountry() != null && filter.getCountry().getCitizenshipCode() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("country").get("citizenshipCode"), filter.getCountry().getCitizenshipCode()));
        }
        criteriaQuery.select(userRoot).where(predicate);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
