package com.alexeysmoliagin.springboot.sportclub.service.activity;

import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateResponse;

import java.util.List;

public interface ActivityService {
    List<ActivityDtoGetResponse> getAllActivity ();
    ActivityDtoGetResponse getActivity (int id);
    ActivityDtoAddResponse addActivity (ActivityDtoAddRequest dto);
    ActivityDtoUpdateResponse updateActivity (ActivityDtoUpdateRequest dto, int id);
    String deleteActivity(int id);

}
