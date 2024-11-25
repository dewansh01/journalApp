package com.dewsjournal.journalApp.service;

import com.dewsjournal.journalApp.entity.JournalEntry;
import com.dewsjournal.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository ;

    public void saveEntry(JournalEntry journalEntry){
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId myID){
        return journalRepository.findById(myID);
    }

    public void deleteById(ObjectId myID){
        journalRepository.deleteById((myID));
    }

//    public List<JournalEntry> getAll(){
//        return journalRepository.findAll();
//    }
}
