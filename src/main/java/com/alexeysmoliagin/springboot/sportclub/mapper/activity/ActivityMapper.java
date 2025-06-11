package com.alexeysmoliagin.springboot.sportclub.mapper.activity;

import com.alexeysmoliagin.springboot.sportclub.controller.activity.model.ActivityModelAddRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.activity.model.ActivityModelResponse;
import com.alexeysmoliagin.springboot.sportclub.controller.activity.model.ActivityModelUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.entity.Activity;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {
    List<ActivityModelResponse> toActivityResponseModel(List<ActivityDtoGetResponse> allActivity);

    ActivityDtoAddRequest toDtoAdd(ActivityModelAddRequest model);

    ActivityDtoUpdateRequest toDtoUpdate(ActivityModelUpdateRequest model);

    ActivityDtoGetResponse toDtoGet(Activity activity);

    ActivityDtoAddResponse toDtoAdd(Activity activity);

    ActivityModelResponse toActivityResponseModel(ActivityDtoGetResponse dto);

    ActivityModelResponse toActivityResponseModel(ActivityDtoAddResponse dto);

    ActivityModelResponse toActivityResponseModel(ActivityDtoUpdateResponse dtoUpdateResponse);

    List<ActivityDtoGetResponse> toActivityDtoList(List<Activity> allActivity);

    Activity toActivity(ActivityDtoAddRequest dto);

    @Mapping(target = "id", ignore = true)
    Activity toUpdateEntity(@MappingTarget Activity activity, ActivityDtoUpdateRequest dto);

    ActivityDtoUpdateResponse toDto(Activity updateActivity);
}
