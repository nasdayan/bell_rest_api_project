package ru.bellintegrator.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.dto.FilteredOrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationFilterDto;
import ru.bellintegrator.practice.organization.dto.OrganizationToSaveDto;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilteredOrganizationDto> getFilteredOrganizations(OrganizationFilterDto filter) {
        List<Organization> organizations = organizationDao.getFilteredOrganizationList(filter);
        return mapOrganizationListToFilteredOrganizationListDto(organizations);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationDto getOrganizationById(Long id) {
        Organization organization = organizationDao.getOrganizationById(id);
        if  (organization == null) {
            throw new ItemNotFoundException("Organization with this id was not found");
        }
        return mapOrganizationEntityToOrganizationDto(organization);
    }

    @Override
    @Transactional
    public void updateOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationDao.getOrganizationById(organizationDto.getId());
        if  (organization == null) {
            throw new ItemNotFoundException("Organization with this id was not found");
        }
        organizationDao.updateOrganization(mapOrganizationDtoToOrganizationEntity(organizationDto));
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationToSaveDto organizationToSaveDto) {
        organizationDao.saveOrganization(mapOrganizationToSaveDtoToOrganizationEntity(organizationToSaveDto));
    }

    private OrganizationDto mapOrganizationEntityToOrganizationDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setName(organization.getName());
        organizationDto.setFullName(organization.getFullName());
        organizationDto.setAddress(organization.getAddress());
        organizationDto.setInn(organization.getInn());
        organizationDto.setKpp(organization.getKpp());
        organizationDto.setPhone(organization.getPhone());
        organizationDto.setIsActive(organization.getIsActive());
        return organizationDto;
    }

    private Organization mapOrganizationToSaveDtoToOrganizationEntity(OrganizationToSaveDto organizationToSaveDto) {
        Organization organization = new Organization();
        organization.setName(organizationToSaveDto.getName());
        organization.setFullName(organizationToSaveDto.getFullName());
        organization.setInn(organizationToSaveDto.getInn());
        organization.setKpp(organizationToSaveDto.getKpp());
        organization.setAddress(organizationToSaveDto.getAddress());
        if (organizationToSaveDto.getPhone() != null) {
            organization.setPhone(organizationToSaveDto.getPhone());
        }
        if (organizationToSaveDto.getIsActive() != null) {
            organization.setIsActive(organizationToSaveDto.getIsActive());
        }
        return organization;
    }

    private Organization mapOrganizationDtoToOrganizationEntity(OrganizationDto organizationDto) {
        Organization organization = organizationDao.getOrganizationById(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setFullName(organizationDto.getFullName());
        organization.setInn(organizationDto.getInn());
        organization.setKpp(organizationDto.getKpp());
        organization.setAddress(organizationDto.getAddress());
        if (organizationDto.getPhone() != null) {
            organization.setPhone(organizationDto.getPhone());
        }
        if (organizationDto.getIsActive() != null) {
            organization.setIsActive(organizationDto.getIsActive());
        }
        return organization;
    }

    private List<FilteredOrganizationDto> mapOrganizationListToFilteredOrganizationListDto(List<Organization> organizationList) {
        List<FilteredOrganizationDto> filteredOrganizations = new ArrayList<>(organizationList.size());
        for (Organization organization : organizationList) {
            FilteredOrganizationDto filteredOrganization = new FilteredOrganizationDto();
            filteredOrganization.setId(organization.getId());
            filteredOrganization.setName(organization.getName());
            filteredOrganization.setIsActive(organization.getIsActive());
            filteredOrganizations.add(filteredOrganization);
        }
        return filteredOrganizations;
    }
}
