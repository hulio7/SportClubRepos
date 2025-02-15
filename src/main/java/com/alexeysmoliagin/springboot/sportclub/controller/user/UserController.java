package com.alexeysmoliagin.springboot.sportclub.controller.user;

import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UsersDto;
import com.alexeysmoliagin.springboot.sportclub.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<UsersResponseModel> showAllUsers() {
        List<UsersDto> allUsers = usersService.getAllUsers();
        return mappingDtoToResponseModel(allUsers);
    }

    @GetMapping("/users/{id}")
    public UsersResponseModel getUser(@PathVariable int id) {
        UsersDto dto = usersService.getUser(id);
        UsersResponseModel responseModel = new UsersResponseModel();
        responseModel.setId(dto.getId());
        responseModel.setAge(dto.getAge());
        responseModel.setGender(dto.getGender());
        responseModel.setName(dto.getName());
        responseModel.setSurname(dto.getSurname());
        responseModel.setTelegramLogin(dto.getTelegramLogin());
        return responseModel;
    }

    @PostMapping("/users")
    public void addNewUser(@RequestBody UserAddRequestModel requestModel) {
        UsersDto dto = new UsersDto();
        dto.setId(requestModel.getId());
        dto.setAge(requestModel.getAge());
        dto.setGender(requestModel.getGender());
        dto.setName(requestModel.getName());
        dto.setSurname(requestModel.getSurname());
        dto.setTelegramLogin(requestModel.getTelegramLogin());
        usersService.saveUser(dto);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody UserUpdateRequestModel requestModel,
                           @PathVariable int id) {
        UsersDto dto = new UsersDto();
        dto.setId(requestModel.getId());
        dto.setAge(requestModel.getAge());
        dto.setGender(requestModel.getGender());
        dto.setName(requestModel.getName());
        dto.setSurname(requestModel.getSurname());
        dto.setTelegramLogin(requestModel.getTelegramLogin());
        usersService.saveUser(dto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUser(id);
    }

    private List<UsersResponseModel> mappingDtoToResponseModel(List<UsersDto> allUsers) {
        List<UsersResponseModel> result = new ArrayList<>();
        for (UsersDto user : allUsers) {
            UsersResponseModel responseModel = new UsersResponseModel();
            responseModel.setId(user.getId());
            responseModel.setAge(user.getAge());
            responseModel.setGender(user.getGender());
            responseModel.setName(user.getName());
            responseModel.setSurname(user.getSurname());
            responseModel.setTelegramLogin(user.getTelegramLogin());
            result.add(responseModel);
        }
        return result;
    }
}








