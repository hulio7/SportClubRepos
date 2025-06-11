package com.alexeysmoliagin.springboot.sportclub.mapper.user;

import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserModelAddRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserModelResponse;
import com.alexeysmoliagin.springboot.sportclub.controller.user.model.UserModelUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDtoAddResponse toUserDtoAdd(User user);

    UserDtoGetResponse toUserDtoGet(User user);

    UserDtoUpdateResponse toUserDtoUpdateResponse(User user);

    User toUser(UserDtoAddRequest dto);

    UserModelResponse toUserResponseModel(UserDtoGetResponse userDto);

    UserModelResponse toUserResponseModel(UserDtoAddResponse userDto);

    List <UserDtoGetResponse> toUserDtoList(List <User> listUsers);

    List <UserModelResponse> toUserResponseModel(List <UserDtoGetResponse> listDto);

    UserModelResponse toUserResponseModel(UserDtoUpdateResponse dto);

    UserDtoAddRequest toUserDtoAddRequest(UserModelAddRequest model);

    UserDtoUpdateRequest toUserDtoUpdateRequest(UserModelUpdateRequest model);

    List<UserModelResponse> toUserResponseModelList(List<UserDtoAddResponse> listDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registerData", ignore = true)
    User updateEntity(@MappingTarget User user, UserDtoUpdateRequest dto);

}
