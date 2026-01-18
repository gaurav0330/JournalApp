package com.gaurav.journalApp.repository;

import com.gaurav.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByusername(String username);

    void deleteByUsername(String username);
}
