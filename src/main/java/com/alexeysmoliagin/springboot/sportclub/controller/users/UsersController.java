package com.alexeysmoliagin.springboot.sportclub.controller.users;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<UsersResponseModel> showAllUsers() {
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
    public UsersResponseModel getUser(@NotNull @PathVariable int id) {
        UsersDto user = usersService.getUser(id);
        UsersResponseModel model = new UsersResponseModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setSurname(user.getSurname());
        model.setGender(user.getGender());
        model.setPhone(user.getPhone());
        model.setTelegramLogin(user.getTelegramLogin());
        model.setAge(user.getAge());
        model.setRegisterData(user.getRegisterData());
        return model;
    }

    @PostMapping("/users")
    public UsersResponseModel addUser(@RequestBody UsersAddRequestModel model) {
        UsersDto dto = new UsersDto();
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setAge(model.getAge());
        dto.setGender(model.getGender());
        dto.setTelegramLogin(model.getTelegramLogin());
        dto.setPhone(model.getPhone());
        usersService.addUser(dto);
        UsersResponseModel usersResponseModelAfterSave = getUser(dto.getId());
        return usersResponseModelAfterSave;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@NotNull @PathVariable int id) {
        return usersService.deleteUser(id);
    }

    @PostMapping("users/{id}")
    public UsersResponseModel updateUser(@NotNull @PathVariable int id, @RequestBody UsersUpdateRequestModel model) {
        UsersDto dto = new UsersDto();
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setAge(model.getAge());
        dto.setGender(model.getGender());
        dto.setTelegramLogin(model.getTelegramLogin());
        dto.setPhone(model.getPhone());
        usersService.updateUser(dto, id);
        UsersResponseModel usersResponseModelAfterSave = getUser(dto.getId());
        return usersResponseModelAfterSave;
    }

    @PostMapping("/users/addArrayUsers")
    public UsersResponseModel addArrayUsers(@RequestBody UsersAddRequestModel[] arrayModels) {
        UsersResponseModel usersResponseModelAfterSave = null;
        for (int i = 0; i < arrayModels.length; i++) {
            UsersDto dto = new UsersDto();
            dto.setName(arrayModels[i].getName());
            dto.setSurname(arrayModels[i].getSurname());
            dto.setAge(arrayModels[i].getAge());
            dto.setGender(arrayModels[i].getGender());
            dto.setTelegramLogin(arrayModels[i].getTelegramLogin());
            dto.setPhone(arrayModels[i].getPhone());
            usersService.addUser(dto);
            usersResponseModelAfterSave = getUser(dto.getId());
        }
        return usersResponseModelAfterSave;
    }
}








