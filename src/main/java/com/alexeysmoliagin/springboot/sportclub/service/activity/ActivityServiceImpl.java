package com.alexeysmoliagin.springboot.sportclub.service.activity;

import com.alexeysmoliagin.springboot.sportclub.mapper.activity.ActivityMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.ActivityRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.entity.Activity;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.alexeysmoliagin.springboot.sportclub.exceptions.ExceptionFactory.entityNotFoundException;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.ActivityMessage.ACTIVITY_DELETE;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.ActivityMessage.ACTIVITY_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory.getMessage;

@RequiredArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    public List<ActivityDtoGetResponse> getAllActivity() {
        var allActivity = activityRepository.findAll();
        return activityMapper.toActivityDtoList(allActivity);
    }

    @Override
    public ActivityDtoGetResponse getActivity(int id) {
        var activity = activityRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(ACTIVITY_NOT_EXIST, id));
        return activityMapper.toDtoGet(activity);
    }

    @Transactional
    @Override
    public ActivityDtoAddResponse addActivity(ActivityDtoAddRequest dto) {
       var activity = activityMapper.toActivity(dto);
       activityRepository.save(activity);
       return activityMapper.toDtoAdd(activity);
    }

    @Transactional
    @Override
    public ActivityDtoUpdateResponse updateActivity(ActivityDtoUpdateRequest dto, int id) {
        var activity = activityRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(ACTIVITY_NOT_EXIST, id));
        Activity updateActivity = activityRepository.save(activityMapper.toUpdateEntity(activity, dto));
        return activityMapper.toDto(updateActivity);
    }

    @Override
    @Transactional
    public String deleteActivity(int id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
            return getMessage(ACTIVITY_DELETE, id);
        }
        throw entityNotFoundException(ACTIVITY_NOT_EXIST, id);
    }
}
