package com.nakul.firstSpringProject.controller;

import com.nakul.firstSpringProject.entity.JournalEntry;
import com.nakul.firstSpringProject.entity.User;
import com.nakul.firstSpringProject.services.JournalEntryService;
import com.nakul.firstSpringProject.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String username){
        User user = userService.getByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(!all.isEmpty()) return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry, @PathVariable String username){
        try{
            journalEntryService.saveEntry(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> entry= journalEntryService.getById(myId);
        if(entry.isPresent())
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{username}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String username, @PathVariable ObjectId myId){
        journalEntryService.deleteById(myId, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{username}/{myId}")
    public ResponseEntity<?> updateEntry(
            @RequestBody JournalEntry newEntry,
            @PathVariable String username,
            @PathVariable ObjectId myId){
        JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(!newEntry.getContent().isEmpty()? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry, username);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
