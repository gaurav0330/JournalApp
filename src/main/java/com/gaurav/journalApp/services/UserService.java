package com.gaurav.journalApp.services;

import com.gaurav.journalApp.entity.User;
import com.gaurav.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  void saveUser(User user){
        try{
            userRepo.save(user);
        } catch (Exception e) {
            log.error("Exception" , e);
        }
    }

    public  void saveNewUser(User user){
        try{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("USER"));
                userRepo.save(user);
        } catch (Exception e) {
            log.error("Error Occurred for {} :",user.getUsername());
            log.warn("warn Occurred");
            log.info("info Occurred");
            log.debug("debug Occurred");
            log.trace("trace Occurred");
        }
    }

    public List<User> getAll(){
        return  userRepo.findAll();
    }

    public Optional<User> getJournalById(ObjectId id){
        return  userRepo.findById(id);
    }

    public void deleteJournalById(ObjectId id){
        userRepo.deleteById(id);
    }

    public void deleteData(){
        userRepo.deleteAll();
    }

    public void deleteByUsername(String username){
        userRepo.deleteByUsername(username);
    }

    public  User findByusername(String username){
        return userRepo.findByusername(username);
    }

    public  void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER,ADMIN"));
        userRepo.save(user);
    }
}
