package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.exceptions.EntityNotFoundException;
import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UsersSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;
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
    private UsersRepository usersRepository;
    @Mock
    private UsersSubscriptionRepository usersSubscriptionRepository;
    @Spy
    private UsersMapperImpl usersMapper;
    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    public void init () {
        MessageSource messageSource = mock(MessageSource.class);
        lenient().when(messageSource.getMessage(any(), any(),any()))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        MessageSourceFactory.setMessageSource(messageSource);
    }

    @Test
    void getAllUsers() {
        Users users = Instancio.create(Users.class);
        when(usersRepository.findAll()).thenReturn(List.of(users));
        usersService.getAllUsers();
        verify(usersRepository).findAll();
    }

    @Test
    @DisplayName("all ok when user exists")
    void getUser() {
        Users users = Instancio.create(Users.class);
        when(usersRepository.findById(users.getId())).thenReturn(Optional.of(users));
        UsersDto actual = usersService.getUser(users.getId());
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
        UsersDto usersDto = Instancio.create(UsersDto.class);
        when(usersRepository.save(any())).thenReturn(any());
        usersService.addUser(usersDto);
        verify(usersRepository).save(any());
    }

    @Test
    @DisplayName("all ok when update user")
    void updateUser() {
        Users users = Instancio.create(Users.class);
        when(usersRepository.findById(anyInt())).thenReturn(Optional.of(users));
        when(usersRepository.save(any())).thenReturn(users);
        UsersDto userDto = Instancio.create(UsersDto.class);
        when(usersMapper.updateEntity(users, userDto)).thenCallRealMethod();
        when(usersMapper.toDto(any(Users.class))).thenCallRealMethod();

        UsersDto actual = usersService.updateUser(userDto, users.getId());
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
        UsersDto usersDto = Instancio.create(UsersDto.class);
        int userId = 123;
        when(usersRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> usersService.updateUser(usersDto, userId));
    }

    @Test
    @DisplayName("all ok when user exists")
    void deleteUser() {
        Users users = Instancio.create(Users.class);
        when(usersRepository.existsById(users.getId())).thenReturn(true);
        usersService.deleteUser(users.getId());
        verify(usersRepository).existsById(users.getId());
        verify(usersRepository).deleteById(users.getId());
    }

    @Test
    @DisplayName("throw NoSuchEntityException when user not exists")
    void deleteUserCase2() {
        when(usersRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, ()-> usersService.deleteUser(anyInt()));
    }
}