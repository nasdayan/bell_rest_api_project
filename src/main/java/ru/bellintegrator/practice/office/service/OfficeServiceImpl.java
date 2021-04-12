package ru.bellintegrator.practice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.common.mapper.MapperFacade;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.dto.FilteredOfficeList;
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeListFilterDto;
import ru.bellintegrator.practice.office.dto.OfficeToSaveDto;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.organizationDao = organizationDao;
    }

    @Override
    public List<FilteredOfficeList> getFilteredOfficeList(OfficeListFilterDto officeListFilterDto) {

        Organization organization = organizationDao.getOrganizationById(officeListFilterDto.getOrgId());
        if (organization == null) {
            throw new ItemNotFoundException("Organizations with this identifier were not found");
        }
        //        Office office = mapperFacade.map(officeListFilter, Office.class);
        Office office = new Office();
        office.setOrganization(organization);
        if (officeListFilterDto.getName() != null) {
            office.setName(officeListFilterDto.getName());
        }
        if (officeListFilterDto.getPhone() != null) {
            office.setPhone(officeListFilterDto.getPhone());
        }
        if (officeListFilterDto.getIsActive() != null) {
            office.setIsActive(officeListFilterDto.getIsActive());
        }
        List<Office> officeList = officeDao.getOfficeFilteredList(office);
        if (officeList.isEmpty()) {
            throw new ItemNotFoundException("Offices with this params were not found");
        }
        return mapperFacade.mapAsList(officeList, FilteredOfficeList.class);
    }

    @Override
    @Transactional(readOnly = true)
    public OfficeDto getOfficeById(Long id) {

        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        return mapperFacade.map(office, OfficeDto.class);
    }

    @Override
    @Transactional
    public void updateOffice(OfficeDto officeDto) {

        Office office = officeDao.getOfficeById(Long.parseLong(officeDto.getId()));
        if (office == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        office = mapperFacade.map(officeDto, Office.class);
        officeDao.updateOffice(office);
    }

    @Override
    @Transactional
    public void saveOffice(OfficeToSaveDto officeToSaveDto) {
        Office office = mapperFacade.map(officeToSaveDto, Office.class);
        Organization organization = organizationDao.getOrganizationById(officeToSaveDto.getOrgId());
        if (organization == null) {
            throw new ItemNotFoundException("Organization with this id was not found");
        }
        officeDao.saveOffice(office);
    }
}
