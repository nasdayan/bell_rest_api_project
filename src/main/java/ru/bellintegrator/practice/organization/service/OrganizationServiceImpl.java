package ru.bellintegrator.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.common.mapper.MapperFacade;
import ru.bellintegrator.practice.exception.ItemNotFoundException;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.dto.FilteredOrganizations;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.organization.dto.OrganizationFilterDto;
import ru.bellintegrator.practice.organization.dto.OrganizationToSaveDto;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilteredOrganizations> getFilteredOrganizations(OrganizationFilterDto organizationFilterDto) {
        Organization organization = mapperFacade.map(organizationFilterDto, Organization.class);
        List<Organization> organizations = organizationDao.getFilteredOrganizationList(organization);
        if (organizations.isEmpty()) {
            throw new ItemNotFoundException("Organizations with such parameters were not found");
        }
        return mapperFacade.mapAsList(organizations, FilteredOrganizations.class);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationDto getOrganizationById(Long id) {
        Organization organization = organizationDao.getOrganizationById(id);
        if  (organization == null) {
            throw new ItemNotFoundException("Organization with this id was not found");
        }
        return mapperFacade.map(organization, OrganizationDto.class);
    }

    @Override
    @Transactional
    public void updateOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationDao.getOrganizationById(organizationDto.getId());
        if  (organization == null) {
            throw new ItemNotFoundException("Organization with this id was not found");
        }
        organization = mapperFacade.map(organizationDto, Organization.class);
        organizationDao.updateOrganization(organization);
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationToSaveDto organizationToSaveDto) {
        Organization organization = mapperFacade.map(organizationToSaveDto, Organization.class);
        organizationDao.saveOrganization(organization);
    }
}
