package com.gaurav.journalApp.scheduler;

import com.gaurav.journalApp.entity.JournalEntry;
import com.gaurav.journalApp.entity.User;
import com.gaurav.journalApp.repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepoImpl userRepo;

    @Scheduled(cron = " 0 0 9 * * SUN")
    public void fetchUserAndSendMail(){
        List<User> users = userRepo.getAllUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String join = String.join(" ",filteredEntries);
            String sentitment = "Good";
        }
    }

}
