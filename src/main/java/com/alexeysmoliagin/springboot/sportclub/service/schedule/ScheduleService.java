package com.alexeysmoliagin.springboot.sportclub.service.schedule;

import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateResponse;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDtoGetResponse> getAllSchedule();

    ScheduleDtoGetResponse getSchedule(int id);

    ScheduleDtoAddResponse addSchedule(ScheduleDtoAddRequest dto);

    ScheduleDtoUpdateResponse updateSchedule(ScheduleDtoUpdateRequest dto, int id);

    String deleteSchedule(int id);
}
