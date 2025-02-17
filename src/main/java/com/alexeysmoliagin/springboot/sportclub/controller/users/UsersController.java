package com.alexeysmoliagin.springboot.sportclub.controller.users;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class UsersController {

    UsersService usersService;

    @GetMapping("/users")
    public List<UsersResponseModel> showAllUsers () {
        List<UsersDto> allUsers = usersService.getAllUsers();
        return mappingDaoToResponseModel(allUsers);
    }

    private List<UsersResponseModel> mappingDaoToResponseModel(List<UsersDto> allUsers) {
        List<UsersResponseModel> result = new ArrayList<>();
        for (UsersDto user : allUsers) {
            UsersResponseModel model = new UsersResponseModel();
            model.setId(user.getId());
            model.setName(user.getName());
            model.setSurname(user.getSurname());
            model.setGender(user.getGender());
            model.setPhone(user.getPhone());
            model.setTelegramLogin(user.getTelegramLogin());
            model.setAge(user.getAge());
            result.add(model);
        }
        return result;
    }

    @GetMapping("/users/{id}")
    public UsersResponseModel showUser (@PathVariable int id) {
        UsersDto user = usersService.getUsers(id);
        UsersResponseModel model = new UsersResponseModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setSurname(user.getSurname());
        model.setGender(user.getGender());
        model.setPhone(user.getPhone());
        model.setTelegramLogin(user.getTelegramLogin());
        model.setAge(user.getAge());
        return model;
    }

    @PostMapping("/users")
    public void saveUser (@RequestBody UsersAddRequestModel model) {
        UsersDto dto = new UsersDto();
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setAge(model.getAge());
        dto.setGender(model.getGender());
        dto.setTelegramLogin(model.getTelegramLogin());
        dto.setId(model.getId());
        dto.setPhone(model.getPhone());
        dto.setRegisterData(model.getRegisterData());
        usersService.addUsers(dto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable int id) {
        usersService.deleteUsers(id);
    }

    @PostMapping("users/{id}")
    public void updateUser (@PathVariable int id, @RequestBody UsersUpdateRequestModel model) {
        UsersDto dto = new UsersDto();
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setAge(model.getAge());
        dto.setGender(model.getGender());
        dto.setTelegramLogin(model.getTelegramLogin());
        dto.setId(model.getId());
        dto.setPhone(model.getPhone());
        dto.setRegisterData(model.getRegisterData());
        usersService.updateUsers(dto, id);
    }
}
