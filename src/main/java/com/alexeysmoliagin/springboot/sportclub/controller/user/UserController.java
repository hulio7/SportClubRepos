package com.alexeysmoliagin.springboot.sportclub.controller.user;

import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserModelAddRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserModelResponse;
import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserModelUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.mapper.user.UserMapper;
import com.alexeysmoliagin.springboot.sportclub.service.user.UserService;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateResponse;
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
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/user")
    public List<UserModelResponse> getAllUsers() {
        List<UserDtoGetResponse> allUsers = userService.getAllUsers();
        return userMapper.toUserResponseModel(allUsers);
    }

    @GetMapping("/user/{id}")
    public UserModelResponse getUser(@NotNull @PathVariable int id) {
        UserDtoGetResponse dto = userService.getUser(id);
        return userMapper.toUserResponseModel(dto);
    }

    @PostMapping("/user")
    public UserModelResponse addUser(@RequestBody UserModelAddRequest model) {
        UserDtoAddResponse dtoResponse = userService.addUser(userMapper.toUserDtoAddRequest(model));
        return userMapper.toUserResponseModel(dtoResponse);
    }

    @PostMapping("/user/addListUsers")
    public List <UserModelResponse> addListUsers(@RequestBody List <UserModelAddRequest> listModelsRequest) {
        List<UserDtoAddResponse> listUserDto = new ArrayList<>();
        for (int i = 0; i < listModelsRequest.size(); i++) {
            UserDtoAddRequest dtoRequest = userMapper.toUserDtoAddRequest(listModelsRequest.get(i));
            UserDtoAddResponse dtoResponse = userService.addUser(dtoRequest);
            listUserDto.add(dtoResponse);
        }
        return userMapper.toUserResponseModelList(listUserDto);
    }

    @PostMapping("user/{id}")
    public UserModelResponse updateUser(@NotNull @PathVariable int id,
                                        @RequestBody UserModelUpdateRequest model) {
        UserDtoUpdateResponse dtoResponse = userService.updateUser(userMapper.toUserDtoUpdateRequest(model), id);
        return userMapper.toUserResponseModel(dtoResponse);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@NotNull @PathVariable int id) {
        return userService.deleteUser(id);
    }


}