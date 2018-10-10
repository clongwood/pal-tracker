package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    public HashMap<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();
    public long count=0;

    public TimeEntry create(TimeEntry timeEntry){
        timeEntry.setId(++count);
        timeEntryMap.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id){
        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list(){
        return new ArrayList<TimeEntry>(timeEntryMap.values());

    }

    public TimeEntry update(long id, TimeEntry timeEntry){
        TimeEntry entry= timeEntryMap.get(id);
        if (entry != null) {
            entry.setDate(timeEntry.getDate());
            entry.setHours(timeEntry.getHours());
            entry.setProjectId(timeEntry.getProjectId());
            entry.setUserId(timeEntry.getUserId());
        }

        return entry;

    }

    public void delete(long id){
             timeEntryMap.remove(id);
    }

}

