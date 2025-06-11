package com.alexeysmoliagin.springboot.sportclub.controller.schedule;

import com.alexeysmoliagin.springboot.sportclub.controller.schedule.model.ScheduleRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.schedule.model.ScheduleResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.schedule.model.ScheduleUpdateModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.schedule.ScheduleMapper;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.ScheduleService;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @GetMapping("/schedule")
    public List<ScheduleResponseModel> getAllSchedule() {
        List<ScheduleDtoGetResponse> allDto = scheduleService.getAllSchedule();
        return scheduleMapper.toScheduleResponseModelList(allDto);
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseModel getSchedule(@NotNull @PathVariable int id) {
        ScheduleDtoGetResponse dto = scheduleService.getSchedule(id);
        return scheduleMapper.toScheduleResponseModel(dto);
    }

    @PostMapping("/schedule")
    public ScheduleResponseModel addSchedule (@RequestBody ScheduleRequestModel model) {
        ScheduleDtoAddResponse dtoResponse = scheduleService.addSchedule(scheduleMapper.toDtoAddRequest(model));
        return scheduleMapper.toScheduleResponseModel(dtoResponse);
    }

    @PostMapping("/schedule/{id}")
    public ScheduleResponseModel updateSchedule (@RequestBody ScheduleUpdateModel model
            , @NotNull @PathVariable int id) {
        ScheduleDtoUpdateResponse dtoResponse = scheduleService
                .updateSchedule(scheduleMapper.toDtoUpdateRequest(model), id);
        return scheduleMapper.toScheduleResponseModel(dtoResponse);
    }

    @DeleteMapping("/schedule/{id}")
    public String deleteSchedule (@NotNull @PathVariable int id) {
        return scheduleService.deleteSchedule(id);
    }
}
