package com.dewsjournal.journalApp.controller;

import com.dewsjournal.journalApp.entity.JournalEntry;
import com.dewsjournal.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/journal")
public class JournalController{

    @Autowired
    private JournalService journalService ;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
         myEntry.setDate(LocalDateTime.now());
         journalService.saveEntry(myEntry);
         return true;
    }

    @GetMapping("/id/{myID}")
    public JournalEntry getEntryById(@PathVariable ObjectId myID){
        return journalService.getEntryById(myID).orElse(null);
    }

    @DeleteMapping("/id/{myID}")
    public void deleteEntryById(@PathVariable ObjectId myID){
         journalService.deleteById(myID) ;
    }

    @PutMapping("/id/{ID}")
    public JournalEntry updateEntryById(@PathVariable ObjectId ID , @RequestBody JournalEntry updatedEntry){
        JournalEntry oldJournalEntry = journalService.getEntryById(ID).orElse(null);
        if(oldJournalEntry != null){
            oldJournalEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ? updatedEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ? updatedEntry.getContent() : oldJournalEntry.getContent());
        }
        journalService.saveEntry(oldJournalEntry);
        return  oldJournalEntry ;
    }
}
