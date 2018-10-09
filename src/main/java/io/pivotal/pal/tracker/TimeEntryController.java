package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    InMemoryTimeEntryRepository timeEntryRepository = new InMemoryTimeEntryRepository();

    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = (InMemoryTimeEntryRepository)timeEntryRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        return new ResponseEntity<>(
                new TimeEntry(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<>(
                new ArrayList<TimeEntry>(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        return new ResponseEntity<>(
            new TimeEntry(), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, TimeEntry timeEntry){

        return new ResponseEntity<>(
                new TimeEntry(), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id){

        return new ResponseEntity<>(
                new TimeEntry(), HttpStatus.OK);

    }

}
