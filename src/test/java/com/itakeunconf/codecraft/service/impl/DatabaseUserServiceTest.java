package com.itakeunconf.codecraft.service.impl;


import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.repository.UserRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

public class DatabaseUserServiceTest {
    private  UserRepository userRepositoryMock;
    private  User user;

    @Before
    public void setup() {
        user = new User();
        user.setUserName("Test_username");
        user.setPasswordHash("Test_password");
        this.userRepositoryMock = Mockito.mock(UserRepository.class);
        Mockito
                .when(userRepositoryMock.save(user))
                .thenReturn(user);

    }


    @Test
    public void should_save_with_success_an_user() {
        this.userRepositoryMock = Mockito.mock(UserRepository.class);
        Mockito
                .when(userRepositoryMock.save(user))
                .thenReturn(user);

        DatabaseUserService userService = new DatabaseUserService(userRepositoryMock);
        Boolean actual =   userService.saveUser("Test_username", "Test_password");
        MatcherAssert.assertThat(actual, Matchers.is(true));
    }

    @Test(expected = Exception.class)
    public void should_not_save_with_success_an_user() {
        this.userRepositoryMock = Mockito.mock(UserRepository.class);
        Mockito
                .when(userRepositoryMock.save(user))
                .thenThrow(new Exception("oups", null));
        DatabaseUserService userService = new DatabaseUserService(userRepositoryMock);
        Boolean actual =   userService.saveUser("Test_username", "Test_password");
        MatcherAssert.assertThat(actual, Matchers.is(false));
    }

}
