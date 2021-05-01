package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.user.dto.FilteredUserList;
import ru.bellintegrator.practice.user.dto.UserByIdDto;
import ru.bellintegrator.practice.user.dto.UserListFilterDto;
import ru.bellintegrator.practice.user.dto.UserToSaveDto;
import ru.bellintegrator.practice.user.dto.UserToUpdateDto;
import ru.bellintegrator.practice.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для управления информацией о пользователях
 */
@Api(value = "UserController", description = "Управление информацией о пользователях")
@RequestMapping(value = "/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение списка пользователей
     * @param userFilter фильтр
     * @return отфильтрованный список пользователей
     */
    @ApiOperation(value = "Получение списка пользователей", httpMethod = "POST")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FilteredUserList> users(@RequestBody UserListFilterDto userFilter) {
        return userService.getFilteredUserList(userFilter);
    }

    /**
     * Получение пользователя по id
     * @param id ИД пользователя
     * @return пользователь
     */
    @ApiOperation(value = "Получение пользователя по id", httpMethod = "GET")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserByIdDto userById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Редактирование информации о пользователе
     * @param userToUpdateDto пользователь, который треб-ся отредактировать
     */
    @ApiOperation(value = "Редактирование информации о пользователе", httpMethod = "POST")
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void userUpdate(@RequestBody @Valid UserToUpdateDto userToUpdateDto) {
        userService.updateUser(userToUpdateDto);
    }

    /**
     * Сохранение информации о пользователе
     * @param user пользователь, который треб-ся сохранить
     */
    @ApiOperation(value = "Сохранение информации о пользователе", httpMethod = "POST")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public void userSave(@RequestBody @Valid UserToSaveDto user) {
        userService.saveUser(user);
    }
}
