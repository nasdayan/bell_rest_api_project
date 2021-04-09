package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

public interface OfficeDao {

    Office getOfficeById(Long id);

    List<Office> getOfficeFilteredList(Office filter);

    void updateOffice(Office office);

    void saveOffice(Office office);
}
