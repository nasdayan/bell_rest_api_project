package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

import java.util.List;

public interface UserDao {

    /**
     * Получение списка пользователей по фильтру
     * @param filter фильтр
     * @return список пользователей
     */
    List<User> getFilteredUserList(User filter);

    /**
     * Получение пользователя по ид
     * @param id ид пользователя
     * @return пользователь
     */
    User getUserById(Long id);

    /**
     * Сохранение пользователя
     * @param user пользователь
     */
    void saveUser(User user);

    /**
     * Редактирование пользователя
     * @param user пользователь
     */
    void updateUser(User user);
}
