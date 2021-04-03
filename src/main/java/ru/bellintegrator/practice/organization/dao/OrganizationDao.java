package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

public interface OrganizationDao {
    List<Organization> getFilteredOrganizationList(Organization filter);

    Organization getOrganizationById(Long id);

    void updateOrganization(Organization organization);

    void saveOrganization(Organization organization);
}
