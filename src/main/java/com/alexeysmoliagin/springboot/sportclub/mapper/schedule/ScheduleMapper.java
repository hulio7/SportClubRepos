package com.alexeysmoliagin.springboot.sportclub.mapper.schedule;

import com.alexeysmoliagin.springboot.sportclub.controller.schedule.model.ScheduleRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.schedule.model.ScheduleResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.schedule.model.ScheduleUpdateModel;
import com.alexeysmoliagin.springboot.sportclub.repository.schedule.entity.Schedule;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {

    List<ScheduleResponseModel> toScheduleResponseModelList(List<ScheduleDtoGetResponse> allDto);

    List<ScheduleDtoGetResponse> toDtoList(List<Schedule> scheduleList);

    ScheduleDtoGetResponse toDtoGet(Schedule schedule);

    ScheduleDtoAddResponse toDtoAdd(Schedule schedule);

    ScheduleDtoUpdateResponse toDto(Schedule model);

    ScheduleResponseModel toScheduleResponseModel(ScheduleDtoGetResponse dto);

    ScheduleResponseModel toScheduleResponseModel(ScheduleDtoUpdateResponse dto);

    ScheduleResponseModel toScheduleResponseModel(ScheduleDtoAddResponse dto);

    Schedule toSchedule (ScheduleDtoAddRequest dto);

    @Mapping(target = "id", ignore = true)
    Schedule toUpdateSchedule(@MappingTarget Schedule schedule, ScheduleDtoUpdateRequest dto);

    ScheduleDtoAddRequest toDtoAddRequest(ScheduleRequestModel model);

    ScheduleDtoUpdateRequest toDtoUpdateRequest(ScheduleUpdateModel model);
}
