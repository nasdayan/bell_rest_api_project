package ru.bellintegrator.practice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.common.mapper.MapperFacade;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.doc.dao.DocDao;
import ru.bellintegrator.practice.doc.model.Doc;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.dto.FilteredUserList;
import ru.bellintegrator.practice.user.dto.UserByIdDto;
import ru.bellintegrator.practice.user.dto.UserListFilterDto;
import ru.bellintegrator.practice.user.dto.UserToSaveDto;
import ru.bellintegrator.practice.user.dto.UserToUpdateDto;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.userDoc.model.UserDoc;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final MapperFacade mapperFacade;
    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final DocDao docDao;
    private final CountryDao countryDao;

    @Autowired
    public UserServiceImpl(MapperFacade mapperFacade, UserDao userDao, OfficeDao officeDao, DocDao docDao, CountryDao countryDao) {
        this.mapperFacade = mapperFacade;
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.docDao = docDao;
        this.countryDao = countryDao;
    }

    @Override
    public List<FilteredUserList> getFilteredUserList(UserListFilterDto userFilter) {
        User user = new User();
        Office office = officeDao.getOfficeById(userFilter.getOfficeId());
        if (office == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        user.setOffice(office);
        if (userFilter.getFirstName() != null) {
            user.setFirstName(userFilter.getFirstName());
        }
        if (userFilter.getSecondName() != null) {
            user.setSecondName(userFilter.getSecondName());
        }
        if (userFilter.getMiddleName() != null) {
            user.setMiddleName(userFilter.getMiddleName());
        }
        if (userFilter.getPosition() != null) {
            user.setPosition(userFilter.getPosition());
        }
        if (userFilter.getDocCode() != null) {
            Doc doc = docDao.getDocByDocCode(userFilter.getDocCode());
            if (doc == null) {
                throw new ItemNotFoundException("Document with such code was not found");
            }
            UserDoc userDoc = new UserDoc();
            userDoc.setDoc(doc);
            user.setUserDoc(userDoc);
        }
        if (userFilter.getCitizenshipCode() != null) {
            Country country = countryDao.getCountryByCitizenshipCode(userFilter.getCitizenshipCode());
            if (country == null) {
                throw new ItemNotFoundException("Country with such citizenship code was not found");
            }
            user.setCountry(country);
        }
        List<User> users = userDao.getFilteredUserList(user);
        if (users.isEmpty()) {
            throw new ItemNotFoundException("Users with such params were not found");
        }
        return mapperFacade.mapAsList(users, FilteredUserList.class);
    }

    @Override
    public UserByIdDto getUserById(Long id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ItemNotFoundException("User with this identifier was not found");
        }
        UserByIdDto userByIdDto = mapperFacade.map(user, UserByIdDto.class);
        if (user.getCountry() != null && user.getCountry().getCitizenshipCode() != null && user.getCountry().getName() != null) {
            userByIdDto.setCitizenshipName(user.getCountry().getName());
            userByIdDto.setCitizenshipCode(user.getCountry().getCitizenshipCode());
        }
//        userByIdView.setDocDate(user.getUserDoc().getDocDate().format(DateTimeFormatter.ISO_DATE));
        if (user.getUserDoc() != null) {
            userByIdDto.setDocDate(user.getUserDoc().getDocDate());
            userByIdDto.setDocNumber(user.getUserDoc().getDocNumber());
            userByIdDto.setDocName(user.getUserDoc().getDoc().getName());
        }
        return userByIdDto;
    }

    @Override
    @Transactional
    public void updateUser(UserToUpdateDto userToUpdateDto) {
        User user = userDao.getUserById(userToUpdateDto.getId());
        if (user == null) {
            throw new ItemNotFoundException("User with such identifier was not found");
        }
        if (userToUpdateDto.getOfficeId() != null) {
            Office office = officeDao.getOfficeById(userToUpdateDto.getOfficeId());
            if (office == null) {
                throw new ItemNotFoundException("Office with such identifier was not found");
            }
            user.setOffice(office);
        }
        user.setFirstName(userToUpdateDto.getFirstName());
        if (userToUpdateDto.getSecondName() != null) {
            user.setSecondName(userToUpdateDto.getSecondName());
        }
        if (userToUpdateDto.getMiddleName() != null) {
            user.setMiddleName(userToUpdateDto.getMiddleName());
        }
        user.setPosition(userToUpdateDto.getPosition());
        if (userToUpdateDto.getPhone() != null) {
            user.setPhone(userToUpdateDto.getPhone());
        }
        UserDoc userDoc = user.getUserDoc();
        if (userDoc == null) {
            userDoc = new UserDoc();
            user.setUserDoc(userDoc);
            userDoc.setUser(user);
        }
        Doc doc = null;
        if (userToUpdateDto.getDocName() != null) {
            if (docDao.getDocByName(userToUpdateDto.getDocName()) == null) {
                throw new ItemNotFoundException("Document with such name was not found");
            }
            doc = docDao.getDocByName(userToUpdateDto.getDocName());
            doc.setName(userToUpdateDto.getDocName());
            String code = docDao.getDocByName(userToUpdateDto.getDocName()).getCode();
            doc.setCode(code);
        }
        if (userToUpdateDto.getDocNumber() != null) {
            userDoc.setDocNumber(userToUpdateDto.getDocNumber());
        }
        if (userToUpdateDto.getDocDate() != null) {
            userDoc.setDocDate(userToUpdateDto.getDocDate());
        }
        userDoc.setDoc(doc);
        if (userToUpdateDto.getCitizenshipCode() != null) {
            Country country = countryDao.getCountryByCitizenshipCode(userToUpdateDto.getCitizenshipCode());
            user.setCountry(country);
        }
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void saveUser(UserToSaveDto userToSaveDto) {
        User user = new User();
        Office office = officeDao.getOfficeById(userToSaveDto.getOfficeId());
        if (office == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        user.setOffice(office);
        user.setFirstName(userToSaveDto.getFirstName());
        if (userToSaveDto.getSecondName() != null) {
            user.setSecondName(userToSaveDto.getSecondName());
        }
        if (userToSaveDto.getMiddleName() != null) {
            user.setMiddleName(userToSaveDto.getMiddleName());
        }
        user.setPosition(userToSaveDto.getPosition());
        if (userToSaveDto.getPhone() != null) {
            user.setPhone(userToSaveDto.getPhone());
        }
        if (userToSaveDto.getDocCode() != null) {
            Doc doc = docDao.getDocByDocCode(userToSaveDto.getDocCode());
            if (doc == null) {
                throw new ItemNotFoundException("Document with such code was not found");
            }
            UserDoc userDoc = new UserDoc();
            userDoc.setDoc(doc);
            userDoc.setUser(user);
            user.setUserDoc(userDoc);
        }
        if (userToSaveDto.getDocCode() == null && userToSaveDto.getDocName() != null) {
            Doc doc = docDao.getDocByName(userToSaveDto.getDocName());
            if (doc == null) {
                throw new ItemNotFoundException("Document with such name was not found");
            }
            UserDoc userDoc = new UserDoc();
            userDoc.setDoc(doc);
            userDoc.setUser(user);
            user.setUserDoc(userDoc);
        }
        if (user.getUserDoc() != null) {
            UserDoc userDoc = user.getUserDoc();
            if (userToSaveDto.getDocNumber() != null) {
                userDoc.setDocNumber(userToSaveDto.getDocNumber());
            }
            if (userToSaveDto.getDocDate() != null) {
                userDoc.setDocDate(userToSaveDto.getDocDate());
            }
        }
        if (userToSaveDto.getIdentified() != null) {
            user.setIdentified(userToSaveDto.getIdentified());
        }
        if (userToSaveDto.getCitizenshipCode() != null) {
            Country country = countryDao.getCountryByCitizenshipCode(userToSaveDto.getCitizenshipCode());
            if (country == null) {
                throw new ItemNotFoundException("Country with such citizenshipCode was not found");
            }
            user.setCountry(country);
        }
        userDao.saveUser(user);
    }
}
