package com.alexeysmoliagin.springboot.sportclub.controller.activity;

import com.alexeysmoliagin.springboot.sportclub.controller.activity.model.ActivityModelAddRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.activity.model.ActivityModelResponse;
import com.alexeysmoliagin.springboot.sportclub.controller.activity.model.ActivityModelUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.mapper.activity.ActivityMapper;
import com.alexeysmoliagin.springboot.sportclub.service.activity.ActivityService;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateResponse;
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
public class ActivityController {

    private final ActivityService activityService;
    private final ActivityMapper activityMapper;

    @GetMapping("/activity")
    public List<ActivityModelResponse> getAllActivity() {
        List<ActivityDtoGetResponse> allActivity = activityService.getAllActivity();
        return activityMapper.toActivityResponseModel(allActivity);
    }

    @GetMapping("/activity/{id}")
    public ActivityModelResponse getActivity (@NotNull @PathVariable int id) {
        ActivityDtoGetResponse dto = activityService.getActivity(id);
        return activityMapper.toActivityResponseModel(dto);
    }

    @PostMapping("/activity")
    public ActivityModelResponse addActivity (@RequestBody ActivityModelAddRequest model) {
        ActivityDtoAddResponse dto = activityService.addActivity(activityMapper.toDtoAdd(model));
        return activityMapper.toActivityResponseModel(dto);
    }

    @PostMapping("/activity/{id}")
    public ActivityModelResponse updateActivity (@NotNull @PathVariable int id,
                                                 @RequestBody ActivityModelUpdateRequest model) {
        ActivityDtoUpdateResponse dtoResponse = activityService.updateActivity(activityMapper.toDtoUpdate(model), id);
        return activityMapper.toActivityResponseModel(dtoResponse);
    }

    @DeleteMapping("/activity/{id}")
    public String deleteActivity (@NotNull @PathVariable int id) {
        return activityService.deleteActivity(id);
    }


}
