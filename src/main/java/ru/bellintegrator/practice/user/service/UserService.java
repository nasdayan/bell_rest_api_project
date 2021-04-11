package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.dto.FilteredUserList;
import ru.bellintegrator.practice.user.dto.UserByIdDto;
import ru.bellintegrator.practice.user.dto.UserListFilterDto;
import ru.bellintegrator.practice.user.dto.UserToSaveDto;
import ru.bellintegrator.practice.user.dto.UserToUpdateDto;

import java.util.List;

public interface UserService {

    /**
     * Получение списка пользователей по фильтру
     * @param userFilter фильтр
     * @return список пользователей
     */
    List<FilteredUserList> getFilteredUserList(UserListFilterDto userFilter);

    /**
     * Получение пользователя по id
     * @param id id пользователя
     * @return пользователь
     */
    UserByIdDto getUserById(Long id);

    /**
     * Редактирование информации о пользователе
     * @param userToUpdateDto пользователь
     */
    void updateUser(UserToUpdateDto userToUpdateDto);

    /**
     * Сохранение информации о пользователе
     * @param userToSaveDto пользователь
     */
    void saveUser(UserToSaveDto userToSaveDto);
}
