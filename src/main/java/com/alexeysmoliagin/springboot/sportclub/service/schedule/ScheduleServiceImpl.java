package com.alexeysmoliagin.springboot.sportclub.service.schedule;

import com.alexeysmoliagin.springboot.sportclub.mapper.schedule.ScheduleMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.ActivityRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.schedule.ScheduleRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.alexeysmoliagin.springboot.sportclub.exceptions.ExceptionFactory.entityNotFoundException;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.ActivityMessage.ACTIVITY_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.ScheduleMessage.SCHEDULE_DELETE;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.ScheduleMessage.SCHEDULE_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.UserMessage.USER_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory.getMessage;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Override
    public List<ScheduleDtoGetResponse> getAllSchedule() {
        var scheduleList = scheduleRepository.findAll();
        return scheduleMapper.toDtoList(scheduleList);
    }

    @Override
    public ScheduleDtoGetResponse getSchedule(int id) {
        var schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(SCHEDULE_NOT_EXIST, id));
        return scheduleMapper.toDtoGet(schedule);

    }

    @Transactional
    @Override
    public ScheduleDtoAddResponse addSchedule(ScheduleDtoAddRequest dto) {
        var schedule = scheduleRepository.save(scheduleMapper.toSchedule(dto));
        return scheduleMapper.toDtoAdd(schedule);
    }

    @Transactional
    @Override
    public ScheduleDtoUpdateResponse updateSchedule(ScheduleDtoUpdateRequest dto, int id) {
        var schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(SCHEDULE_NOT_EXIST, id));
        var activity = activityRepository.findById(dto.getActivityId())
                .orElseThrow(() -> entityNotFoundException(ACTIVITY_NOT_EXIST, dto.getActivityId()));
        var user = userRepository.findById(dto.getCoachId())
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, dto.getCoachId()));
        var updateSchedule = scheduleRepository.save(scheduleMapper.toUpdateSchedule(schedule, dto));
        return scheduleMapper.toDto(updateSchedule);
    }

    @Transactional
    @Override
    public String deleteSchedule(int id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return getMessage(SCHEDULE_DELETE, id);
        }
        throw entityNotFoundException(SCHEDULE_NOT_EXIST, id);
    }
}
