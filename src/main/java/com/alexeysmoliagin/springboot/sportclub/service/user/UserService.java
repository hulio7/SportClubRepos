package com.alexeysmoliagin.springboot.sportclub.service.user;

import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateResponse;

import java.util.List;

public interface UserService {
    List <UserDtoGetResponse> getAllUsers ();
    UserDtoGetResponse getUser (int id);
    UserDtoAddResponse addUser (UserDtoAddRequest dto);
    UserDtoUpdateResponse updateUser (UserDtoUpdateRequest dto, int id);
    String deleteUser (int id);
}
