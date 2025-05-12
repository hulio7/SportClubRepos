package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.exceptions.EntityNotFoundException;
import com.alexeysmoliagin.springboot.sportclub.mapper.user.UserMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.repository.usersubscription.UserSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UserDto;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserSubscriptionRepository userSubscriptionRepository;
    @Spy
    private UserMapperImpl userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init () {
        MessageSource messageSource = mock(MessageSource.class);
        lenient().when(messageSource.getMessage(any(), any(),any()))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        MessageSourceFactory.setMessageSource(messageSource);
    }

    @Test
    void getAllUsers() {
        User user = Instancio.create(User.class);
        when(userRepository.findAll()).thenReturn(List.of(user));
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("all ok when user exists")
    void getUser() {
        User user = Instancio.create(User.class);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        UserDto actual = userService.getUser(user.getId());
        assertEquals(user.getAge(), actual.getAge());
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getGender(), actual.getGender());
        assertEquals(user.getPhone(), actual.getPhone());
        assertEquals(user.getSurname(), actual.getSurname());
        verify(userRepository).findById(user.getId());
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void getUserCase2 () {
        MessageSource messageSource = mock(MessageSource.class);
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> userService.getUser(anyInt()));
    }

    @Test
    @DisplayName("all ok when correct data")
    void addUser() {
        UserDto userDto = Instancio.create(UserDto.class);
        when(userRepository.save(any())).thenReturn(any());
        userService.addUser(userDto);
        verify(userRepository).save(any());
    }

    @Test
    @DisplayName("all ok when update user")
    void updateUser() {
        User user = Instancio.create(User.class);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDto userDto = Instancio.create(UserDto.class);
        when(userMapper.updateEntity(user, userDto)).thenCallRealMethod();
        when(userMapper.toDto(any(User.class))).thenCallRealMethod();

        UserDto actual = userService.updateUser(userDto, user.getId());
        assertEquals(user.getAge(), actual.getAge());
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getGender(), actual.getGender());
        assertEquals(user.getPhone(), actual.getPhone());
        assertEquals(user.getSurname(), actual.getSurname());
        verify(userRepository).findById(user.getId());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void updateUserCase2 () {
        UserDto userDto = Instancio.create(UserDto.class);
        int userId = 123;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> userService.updateUser(userDto, userId));
    }

    @Test
    @DisplayName("all ok when user exists")
    void deleteUser() {
        User user = Instancio.create(User.class);
        when(userRepository.existsById(user.getId())).thenReturn(true);
        userService.deleteUser(user.getId());
        verify(userRepository).existsById(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void deleteUserCase2() {
        when(userRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, ()-> userService.deleteUser(anyInt()));
    }
}