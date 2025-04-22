package com.alexeysmoliagin.springboot.sportclub.controller.users;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.service.users.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @GetMapping("/users")
    public List<UsersResponseModel> showAllUsers() {
        List<UsersDto> allUsers = usersService.getAllUsers();
        return usersMapper.toUsersResponseModel(allUsers);
    }

    @GetMapping("/users/{id}")
    public UsersResponseModel getUser(@NotNull @PathVariable int id) {
        UsersDto usersDto = usersService.getUser(id);
        return usersMapper.toResponseModel(usersDto);
    }

    @PostMapping("/users")
    public UsersResponseModel addUser(@RequestBody UsersAddRequestModel model) {
        UsersDto dto = usersService.addUser(usersMapper.toDto(model));
        return usersMapper.toResponseModel(dto);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@NotNull @PathVariable int id) {
        return usersService.deleteUser(id);
    }

    @PostMapping("users/{id}")
    public UsersResponseModel updateUser(@NotNull @PathVariable int id,
                                         @RequestBody UsersUpdateRequestModel model) {
        UsersDto dto = usersService.updateUser(usersMapper.toDto(model), id);
        return usersMapper.toResponseModel(dto);
    }

    @PostMapping("/users/addArrayUsers")
    public List <UsersResponseModel> addListUsers(@RequestBody List <UsersAddRequestModel> listModelsRequest) {
        List<UsersDto> listUserDto = new ArrayList<>();
        for (int i = 0; i < listModelsRequest.size(); i++) {
            UsersDto dto = usersMapper.toDto(listModelsRequest.get(i));
            dto = usersService.addUser(dto);
            listUserDto.add(dto);
        }
        return usersMapper.toUsersResponseModel(listUserDto);
    }
}















