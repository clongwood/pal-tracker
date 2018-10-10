package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap<Long, TimeEntry> imMemoryStorage;
    private long counter = 0L;


    public InMemoryTimeEntryRepository () {
        imMemoryStorage = new HashMap<>();
    }

    public TimeEntry create(TimeEntry timeEntry) {
        counter++;
        Long key = counter;
        timeEntry.setId(key);
        imMemoryStorage.put(key, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long l) {
        Long key = l;
        return imMemoryStorage.get(key);
    }

    public List<TimeEntry> list() {

        return new ArrayList(imMemoryStorage.values());
    }


    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        Long key = eq;

        TimeEntry updateEntry =  imMemoryStorage.get (key);
        if (updateEntry != null) {

            updateEntry.setDate(any.getDate());
            updateEntry.setHours(any.getHours());
            updateEntry.setProjectId(any.getProjectId());
            updateEntry.setUserId(any.getUserId());
        }
        return updateEntry;
    }


    public TimeEntry delete(long l) {
        Long key = l;
        TimeEntry deleteEntry =  imMemoryStorage.remove(key);
        return deleteEntry;
    }
}








