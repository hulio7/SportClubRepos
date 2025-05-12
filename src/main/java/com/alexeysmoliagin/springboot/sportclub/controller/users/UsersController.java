package com.alexeysmoliagin.springboot.sportclub.controller.users;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UserAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UserResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UserUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.user.UserMapper;
import com.alexeysmoliagin.springboot.sportclub.service.users.UserService;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public List<UserResponseModel> showAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return userMapper.toUsersResponseModel(allUsers);
    }

    @GetMapping("/users/{id}")
    public UserResponseModel getUser(@NotNull @PathVariable int id) {
        UserDto userDto = userService.getUser(id);
        return userMapper.toResponseModel(userDto);
    }

    @PostMapping("/users")
    public UserResponseModel addUser(@RequestBody UserAddRequestModel model) {
        UserDto dto = userService.addUser(userMapper.toDto(model));
        return userMapper.toResponseModel(dto);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@NotNull @PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("users/{id}")
    public UserResponseModel updateUser(@NotNull @PathVariable int id,
                                        @RequestBody UserUpdateRequestModel model) {
        UserDto dto = userService.updateUser(userMapper.toDto(model), id);
        return userMapper.toResponseModel(dto);
    }

    @PostMapping("/users/addArrayUsers")
    public List <UserResponseModel> addListUsers(@RequestBody List <UserAddRequestModel> listModelsRequest) {
        List<UserDto> listUserDto = new ArrayList<>();
        for (int i = 0; i < listModelsRequest.size(); i++) {
            UserDto dto = userMapper.toDto(listModelsRequest.get(i));
            dto = userService.addUser(dto);
            listUserDto.add(dto);
        }
        return userMapper.toUsersResponseModel(listUserDto);
    }
}















