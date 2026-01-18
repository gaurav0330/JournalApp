package com.gaurav.journalApp.repository;

import com.gaurav.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository <JournalEntry , ObjectId>{

}
