package com.gaurav.journalApp.controller;

import com.gaurav.journalApp.entity.User;
import com.gaurav.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void addNewUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


}
