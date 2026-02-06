package com.gaurav.journalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
    public void testCriteria(){
        Assertions.assertNotNull(userRepo.getAllUserForSA());
    }
}
