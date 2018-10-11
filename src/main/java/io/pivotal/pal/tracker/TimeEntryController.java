package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;



    private CounterService counter;
    private GaugeService gauge;


    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            CounterService counter,
            GaugeService gauge
    ) {
        this.timeEntryRepository = timeEntriesRepo;
        this.counter = counter;
        this.gauge = gauge;
    }


    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry entry = timeEntryRepository.find((id));
        if (entry != null) {
            counter.increment("TimeEntry.read");
            return new ResponseEntity<>(
                    entry, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(
                    entry, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> entryList = timeEntryRepository.list();
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(
                entryList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);

    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id,@RequestBody TimeEntry timeEntry){
        TimeEntry entry=timeEntryRepository.update(id,timeEntry);
        if (entry != null) {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<>(
                    entry, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(
                    entry, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
