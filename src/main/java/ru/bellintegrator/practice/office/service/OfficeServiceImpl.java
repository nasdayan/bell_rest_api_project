package ru.bellintegrator.practice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.dto.FilteredOfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeListFilterDto;
import ru.bellintegrator.practice.office.dto.OfficeToSaveDto;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    @Override
    @Transactional
    public List<FilteredOfficeDto> getFilteredOfficeList(OfficeListFilterDto officeListFilterDto) {
        List<Office> officeList = officeDao.getOfficeFilteredList(officeListFilterDto);
        return mapOfficeListToFilteredOfficeList(officeList);
    }

    @Override
    @Transactional(readOnly = true)
    public OfficeDto getOfficeById(Long id) {
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        return mapOfficeEntityToOfficeDto(office);
    }

    @Override
    @Transactional
    public void updateOffice(OfficeDto officeDto) {
        officeDao.updateOffice(mapOfficeUpdateDtoToOfficeEntity(officeDto));
    }

    @Override
    @Transactional
    public void saveOffice(OfficeToSaveDto officeToSaveDto) {
        officeDao.saveOffice(mapOfficeToSaveDtoToOfficeEntity(officeToSaveDto));
    }

    private OfficeDto mapOfficeEntityToOfficeDto(Office office) {
        OfficeDto officeDto = new OfficeDto();
        officeDto.setId(office.getId());
        officeDto.setName(office.getName());
        officeDto.setAddress(office.getAddress());
        officeDto.setPhone(office.getPhone());
        officeDto.setIsActive(office.getIsActive());
        return officeDto;
    }

    private Office mapOfficeUpdateDtoToOfficeEntity(OfficeDto officeDto) {
        Office office = officeDao.getOfficeById(officeDto.getId());
        if (office == null) {
            throw new ItemNotFoundException("Office with this identifier was not found");
        }
        office.setName(officeDto.getName());
        office.setAddress(officeDto.getAddress());
        if (officeDto.getPhone() != null) {
            office.setPhone(officeDto.getPhone());
        }
        if (officeDto.getIsActive() != null) {
            office.setIsActive(officeDto.getIsActive());
        }
        return office;
    }

    private Office mapOfficeToSaveDtoToOfficeEntity(OfficeToSaveDto officeToSaveDto) {
        Office office = new Office();
        Organization organization = organizationDao.getOrganizationById(officeToSaveDto.getOrgId());
        if (organization == null) {
            throw new ItemNotFoundException("Organization with this id was not found");
        }
        office.setOrganization(organization);
        if (officeToSaveDto.getName() != null) {
            office.setName(officeToSaveDto.getName());
        }
        if (officeToSaveDto.getAddress() != null) {
            office.setAddress(officeToSaveDto.getAddress());
        }
        if (officeToSaveDto.getPhone() != null) {
            office.setPhone(officeToSaveDto.getPhone());
        }
        if (officeToSaveDto.getIsActive() != null) {
            office.setIsActive(officeToSaveDto.getIsActive());
        }
        return office;
    }

    private List<FilteredOfficeDto> mapOfficeListToFilteredOfficeList(List<Office> offices) {
        List<FilteredOfficeDto> filteredOffices = new ArrayList<>(offices.size());
        for (Office office : offices) {
            FilteredOfficeDto filteredOffice = new FilteredOfficeDto();
            filteredOffice.setId(office.getId());
            filteredOffice.setName(office.getName());
            filteredOffice.setIsActive(office.getIsActive());
            filteredOffices.add(filteredOffice);
        }
        return filteredOffices;
    }
}
