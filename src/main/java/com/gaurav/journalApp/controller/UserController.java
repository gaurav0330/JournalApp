package com.gaurav.journalApp.controller;

import com.gaurav.journalApp.api.response.WeatherResponce;
import com.gaurav.journalApp.entity.User;
import com.gaurav.journalApp.services.UserService;
import com.gaurav.journalApp.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;


//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers(){
//            List<User> all = userService.getAll();
//            if(all != null && !all.isEmpty()){
//                return  new ResponseEntity<>(all, HttpStatus.OK);
//             }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User userInDb = userService.findByusername(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);

        return new ResponseEntity<>(userInDb,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<String> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        WeatherResponce weatherRes = weatherService.getWeather("Mumbai");
        String greeting = "";

        if(weatherRes != null && weatherRes.getCurrent() != null){
            greeting = ", Weathers Feels like " + weatherRes.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("HI " + username + greeting,HttpStatus.OK);
    }

}
