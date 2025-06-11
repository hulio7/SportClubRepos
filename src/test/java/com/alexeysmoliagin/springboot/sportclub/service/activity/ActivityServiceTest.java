package com.alexeysmoliagin.springboot.sportclub.service.activity;

import com.alexeysmoliagin.springboot.sportclub.exceptions.EntityNotFoundException;
import com.alexeysmoliagin.springboot.sportclub.mapper.activity.ActivityMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.ActivityRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.activity.entity.Activity;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.activity.dto.ActivityDtoUpdateResponse;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {
    @Mock    //Задаем поведение репозитория
    private ActivityRepository activityRepository;
    @InjectMocks
    private ActivityServiceImpl activityService;
    @Spy
    private ActivityMapperImpl activityMapper;

    @BeforeEach
    public void init () {
        MessageSource messageSource = mock(MessageSource.class);
        lenient().when(messageSource.getMessage(any(), any(),any()))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        MessageSourceFactory.setMessageSource(messageSource);
    }

    @Test
    void getAllActivity() {
        //prepare
        Activity expected = Instancio.create(Activity.class);
        when(activityRepository.findAll()).thenReturn(List.of(expected));
        when(activityMapper.toActivityDtoList(anyList())).thenCallRealMethod();
        //action
        List<ActivityDtoGetResponse> actual = activityService.getAllActivity();
        //assertions
        assertEquals(1, actual.size());
        var itemFromResponse = actual.get(0);
        assertAll(
                ()-> assertEquals(expected.getId(), itemFromResponse.getId()),
                ()-> assertEquals(expected.getName(), itemFromResponse.getName()),
                ()-> assertEquals(expected.getMinAge(), itemFromResponse.getMinAge())
        );
        verify(activityRepository).findAll();
    }

    @Test
    @DisplayName("all ok when activity exists")
    void getActivity() {
        Activity expected = Instancio.create(Activity.class);
        when(activityRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        ActivityDtoGetResponse actual = activityService.getActivity(expected.getId());
        assertAll(
                () -> assertEquals(expected.getMinAge(), actual.getMinAge()),
                () -> assertEquals(expected.getName(), actual.getName())
        );
        verify(activityRepository).findById(expected.getId());
    }

    @Test
    @DisplayName("throw NoSuchActivityException when activity not exists")
    void getActivityCase2() {
        when(activityRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->activityService.getActivity(anyInt()));
    }

    @Test
    @DisplayName("all ok when correct data")
    void addActivity() {
        ActivityDtoAddRequest dto = Instancio.create(ActivityDtoAddRequest.class);
        when(activityRepository.save(any())).thenReturn(any());
        activityService.addActivity(dto);
        verify(activityRepository).save(any());
    }

    @Test
    @DisplayName("all ok when update user")
    void updateActivity() {
        ActivityDtoUpdateRequest dtoRequest = Instancio.create(ActivityDtoUpdateRequest.class);
        Activity activity = Instancio.create(Activity.class);
        when(activityRepository.findById(anyInt())).thenReturn(Optional.of(activity));
        when(activityMapper.toUpdateEntity(any(),any())).thenCallRealMethod();
        when(activityRepository.save(any())).thenReturn(activity);
        when(activityMapper.toDto(any())).thenCallRealMethod();
        ActivityDtoUpdateResponse actual = activityService.updateActivity(dtoRequest, activity.getId());
        assertEquals(dtoRequest.getName(), actual.getName());
        assertEquals(dtoRequest.getMinAge(), actual.getMinAge());
        verify(activityRepository).findById(anyInt());
        verify(activityMapper).toUpdateEntity(any(), any());
        verify(activityRepository).save(any());
        verify(activityMapper).toDto(any());
    }

    @Test
    @DisplayName("throw entityNotFoundException when activity not exist")
    void updateActivityCase2() {
        ActivityDtoUpdateRequest dto = Instancio.create(ActivityDtoUpdateRequest.class);
        int id = 123;
        when(activityRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> activityService.updateActivity(dto, id));
    }

    @DisplayName("all ok when correct data")
    @Test
    void deleteActivity() {
        Activity activity = Instancio.create(Activity.class);
        when(activityRepository.existsById(activity.getId())).thenReturn(true);
        activityService.deleteActivity(activity.getId());
        verify(activityRepository).existsById(anyInt());
        verify(activityRepository).deleteById(anyInt());
    }

    @DisplayName("throw entityNotFoundException when activity not exist")
    @Test
    void deleteActivityCase2() {
        when(activityRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, ()-> activityService.deleteActivity(anyInt()));
        verify(activityRepository).existsById(anyInt());
    }
}