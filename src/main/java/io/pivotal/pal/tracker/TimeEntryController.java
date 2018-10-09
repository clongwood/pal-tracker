package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        repository = timeEntryRepository;
    }

    @PostMapping("/")
    public ResponseEntity create (TimeEntry timeEntry) {
        TimeEntry savedEntry = repository.create(timeEntry);
        return new ResponseEntity(savedEntry, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<TimeEntry> read(long id) {
        TimeEntry timeEntry = repository.find(id);
        if (timeEntry != null)
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);

    }
    @GetMapping("/list")
    public ResponseEntity<List<TimeEntry>> list() {

        return null; // return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity update(long id, TimeEntry expected) {
        TimeEntry timeEntry = repository.update(id, expected);
        if (timeEntry != null)
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete")
    public ResponseEntity<TimeEntry> delete(long id) {
        TimeEntry timeEntry = repository.delete(id);
        if (timeEntry != null)
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity(timeEntry, HttpStatus.NO_CONTENT);
    }
}
