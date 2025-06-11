package com.alexeysmoliagin.springboot.sportclub.service.schedule;

import com.alexeysmoliagin.springboot.sportclub.exceptions.EntityNotFoundException;
import com.alexeysmoliagin.springboot.sportclub.mapper.schedule.ScheduleMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.ActivityRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.entity.Activity;
import com.alexeysmoliagin.springboot.sportclub.repository.schedule.ScheduleRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.schedule.entity.Schedule;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.schedule.dto.ScheduleDtoUpdateRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Spy
    private ScheduleMapperImpl scheduleMapper;
    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    public void init () {
        MessageSource messageSource = mock(MessageSource.class);
        lenient().when(messageSource.getMessage(any(), any(),any()))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        MessageSourceFactory.setMessageSource(messageSource);
    }

    @DisplayName("all ok when return list of schedules")
    @Test
    void getAllSchedule() {
        Schedule expected = Instancio.create(Schedule.class);
        when(scheduleRepository.findAll()).thenReturn(List.of(expected));
        when(scheduleMapper.toDtoList(anyList())).thenCallRealMethod();
        List<ScheduleDtoGetResponse>list = scheduleService.getAllSchedule();
        assertEquals(1, list.size());
        ScheduleDtoGetResponse actual = list.get(0);
        assertAll(
                ()->assertEquals(expected.getName(), actual.getName()),
                ()->assertEquals(expected.getId(), actual.getId()),
                ()->assertEquals(expected.getEndTime(), actual.getEndTime()),
                ()->assertEquals(expected.getCoachId(), actual.getCoachId()),
                ()->assertEquals(expected.getActivityId(), actual.getActivityId()),
                ()->assertEquals(expected.getStartTime(), actual.getStartTime())
                );
        verify(scheduleRepository).findAll();
        verify(scheduleMapper).toDtoList(anyList());
    }

    @DisplayName("all ok when correct data")
    @Test
    void getSchedule() {
        Schedule expected = Instancio.create(Schedule.class);
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        when(scheduleMapper.toDtoGet(any())).thenCallRealMethod();
        ScheduleDtoGetResponse actual = scheduleService.getSchedule(expected.getId());
        assertAll(
                ()->assertEquals(expected.getName(), actual.getName()),
                ()->assertEquals(expected.getId(), actual.getId()),
                ()->assertEquals(expected.getActivityId(), actual.getActivityId()),
                ()->assertEquals(expected.getCoachId(), actual.getCoachId()),
                ()->assertEquals(expected.getStartTime(), actual.getStartTime()),
                ()->assertEquals(expected.getEndTime(), actual.getEndTime())
                );
        verify(scheduleRepository).findById(anyInt());
        verify(scheduleMapper).toDtoGet(any());
    }

    @DisplayName("throw entityNotFoundException when schedule not exist")
    @Test
    void getScheduleCase2() {
        Schedule schedule = Instancio.create(Schedule.class);
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> scheduleService.getSchedule(schedule.getId()));
        verify(scheduleRepository).findById(anyInt());
    }

    @Test
    void addSchedule() {
        ScheduleDtoAddRequest dto = Instancio.create(ScheduleDtoAddRequest.class);
        Schedule schedule = Instancio.create(Schedule.class);
        when(scheduleMapper.toSchedule(any())).thenCallRealMethod();
        when(scheduleRepository.save(any())).thenReturn(schedule);
        when(scheduleMapper.toSchedule(any())).thenCallRealMethod();
        scheduleService.addSchedule(dto);
        verify(scheduleMapper).toSchedule(any());
        verify(scheduleRepository).save(any());
        verify(scheduleMapper).toDtoAdd(any());
    }

    @DisplayName("all ok when correct data")
    @Test
    void updateSchedule() {
        ScheduleDtoUpdateRequest dto = Instancio.create(ScheduleDtoUpdateRequest.class);
        Schedule schedule = Instancio.create(Schedule.class);
        Activity activity = Instancio.create(Activity.class);
        Schedule newSchedule = Instancio.create(Schedule.class);
        User user = Instancio.create(User.class);
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(schedule));
        when(activityRepository.findById(anyInt())).thenReturn(Optional.of(activity));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(scheduleMapper.toUpdateSchedule(any(), any())).thenCallRealMethod();
        when(scheduleRepository.save(any())).thenReturn(newSchedule);
        when(scheduleMapper.toDto(any())).thenCallRealMethod();
        scheduleService.updateSchedule(dto, schedule.getId());
        verify(scheduleRepository).findById(anyInt());
        verify(activityRepository).findById(anyInt());
        verify(userRepository).findById(anyInt());
        verify(scheduleMapper).toUpdateSchedule(any(), any());
        verify(scheduleRepository).save(any());
        verify(scheduleMapper).toDto(any());
    }

    @DisplayName("throw entityNotFoundException when schedule not exist")
    @Test
    void updateScheduleCase2() {
        ScheduleDtoUpdateRequest dto = Instancio.create(ScheduleDtoUpdateRequest.class);
        Schedule schedule = Instancio.create(Schedule.class);
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->scheduleService.updateSchedule(dto, schedule.getId()));
    }

    @DisplayName("throw entityNotFoundException when activity not exist")
    @Test
    void updateScheduleCase3() {
        ScheduleDtoUpdateRequest dto = Instancio.create(ScheduleDtoUpdateRequest.class);
        Schedule schedule = Instancio.create(Schedule.class);
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(schedule));
        when(activityRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->scheduleService.updateSchedule(dto, schedule.getId()));
    }

    @DisplayName("throw entityNotFoundException when user not exist")
    @Test
    void updateScheduleCase4() {
        ScheduleDtoUpdateRequest dto = Instancio.create(ScheduleDtoUpdateRequest.class);
        Schedule schedule = Instancio.create(Schedule.class);
        Activity activity = Instancio.create(Activity.class);
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(schedule));
        when(activityRepository.findById(anyInt())).thenReturn(Optional.of(activity));
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->scheduleService.updateSchedule(dto, schedule.getId()));
    }

    @DisplayName("all ok when correct data")
    @Test
    void deleteSchedule() {
        Schedule schedule = Instancio.create(Schedule.class);
        when(scheduleRepository.existsById(anyInt())).thenReturn(true);
        scheduleService.deleteSchedule(schedule.getId());
        verify(scheduleRepository).existsById(anyInt());
        verify(scheduleRepository).existsById(anyInt());
    }

    @DisplayName("throw entityNotFoundException when schedule not exist")
    @Test
    void deleteScheduleCase2() {
        Schedule schedule = Instancio.create(Schedule.class);
        when(scheduleRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, ()->scheduleService.deleteSchedule(schedule.getId()));
    }
}