package com.gaurav.journalApp.services;

import com.gaurav.journalApp.entity.JournalEntry;
import com.gaurav.journalApp.entity.User;
import com.gaurav.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public  void saveEntry(JournalEntry journalEntry , String username){
        try{
            User user = userService.findByusername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUsername(null);
            userService.saveUser(user);

        } catch (Exception e) {
            System.out.println(e);
            throw  new RuntimeException("Something went wrong",e);
        }
    }

    public  void saveEntry(JournalEntry journalEntry){
        try{
            JournalEntry saved = journalEntryRepo.save(journalEntry);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<JournalEntry> getAll(){
        return  journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getJournalById(ObjectId id){
        return  journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteJournalById(ObjectId id,String username){
        boolean removed = false;
        try {
            User user = userService.findByusername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {

            throw new RuntimeException("AN error occurred while deleting the user", e);
        }
        return removed;
    }

    public void deleteData(){
        journalEntryRepo.deleteAll();
    }

}
