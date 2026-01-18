package com.gaurav.journalApp.service;

import com.gaurav.journalApp.entity.User;
import com.gaurav.journalApp.repository.UserRepo;
import com.gaurav.journalApp.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@Disabled()
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private  UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        User mockUser = User.builder()
                .username("test1")
                .password("sdfsf")
                .roles(new ArrayList<>())
                .build();

        when(userRepo.findByusername(ArgumentMatchers.anyString())).thenReturn(mockUser);
        UserDetails user= userDetailsService.loadUserByUsername("test1");
        Assertions.assertNotNull(user);
    }

}
