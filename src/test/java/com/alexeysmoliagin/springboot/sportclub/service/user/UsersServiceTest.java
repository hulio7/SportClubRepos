package com.alexeysmoliagin.springboot.sportclub.service.user;

import com.alexeysmoliagin.springboot.sportclub.exceptions.EntityNotFoundException;
import com.alexeysmoliagin.springboot.sportclub.mapper.user.UserMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.repository.userSubscription.UserSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateResponse;
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
class UsersServiceTest {
    @Mock
    private UserRepository usersRepository;

    @Mock
    private UserSubscriptionRepository userSubscriptionRepository;

    @Spy
    private UserMapperImpl usersMapper;
    @InjectMocks
    private UserServiceImpl usersService;

    @BeforeEach
    public void init () {
        MessageSource messageSource = mock(MessageSource.class);
        lenient().when(messageSource.getMessage(any(), any(),any()))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        MessageSourceFactory.setMessageSource(messageSource);
    }

    @Test
    void getAllUsers() {
        User users = Instancio.create(User.class);
        when(usersRepository.findAll()).thenReturn(List.of(users));
        usersService.getAllUsers();
        verify(usersRepository).findAll();
    }

    @Test
    @DisplayName("all ok when user exists")
    void getUser() {
        User users = Instancio.create(User.class);
        when(usersRepository.findById(users.getId())).thenReturn(Optional.of(users));
        UserDtoGetResponse actual = usersService.getUser(users.getId());
        assertEquals(users.getAge(), actual.getAge());
        assertEquals(users.getName(), actual.getName());
        assertEquals(users.getGender(), actual.getGender());
        assertEquals(users.getPhone(), actual.getPhone());
        assertEquals(users.getSurname(), actual.getSurname());
        verify(usersRepository).findById(users.getId());
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void getUserCase2 () {
        MessageSource messageSource = mock(MessageSource.class);
        when(usersRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> usersService.getUser(anyInt()));
    }

    @Test
    @DisplayName("all ok when correct data")
    void addUser() {
        UserDtoAddRequest dto = Instancio.create(UserDtoAddRequest.class);
        when(usersRepository.save(any())).thenReturn(any());
        usersService.addUser(dto);
        verify(usersRepository).save(any());
    }

    @Test
    @DisplayName("all ok when update user")
    void updateUser() {
        User users = Instancio.create(User.class);
        when(usersRepository.findById(anyInt())).thenReturn(Optional.of(users));
        when(usersRepository.save(any())).thenReturn(users);
        var userDto = Instancio.create(UserDtoUpdateRequest.class);
        when(usersMapper.updateEntity(users, userDto)).thenCallRealMethod();
        when(usersMapper.toUserDtoUpdateResponse(any(User.class))).thenCallRealMethod();

        UserDtoUpdateResponse actual = usersService.updateUser(userDto, users.getId());
        assertEquals(users.getAge(), actual.getAge());
        assertEquals(users.getName(), actual.getName());
        assertEquals(users.getGender(), actual.getGender());
        assertEquals(users.getPhone(), actual.getPhone());
        assertEquals(users.getSurname(), actual.getSurname());
        verify(usersRepository).findById(users.getId());
        verify(usersRepository).save(users);
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void updateUserCase2 () {
        var usersDto = Instancio.create(UserDtoUpdateRequest.class);
        int userId = 123;
        when(usersRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> usersService.updateUser(usersDto, userId));
    }

    @Test
    @DisplayName("all ok when user exists")
    void deleteUser() {
        User users = Instancio.create(User.class);
        when(usersRepository.existsById(users.getId())).thenReturn(true);
        usersService.deleteUser(users.getId());
        verify(usersRepository).existsById(users.getId());
        verify(userSubscriptionRepository).deleteByUserId(users.getId());
        verify(usersRepository).deleteById(users.getId());
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void deleteUserCase2() {
        when(usersRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, ()-> usersService.deleteUser(anyInt()));
    }
}