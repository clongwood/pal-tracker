package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    public HashMap<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry){
        timeEntryMap.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id){
        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list(){
        return new ArrayList<TimeEntry>();

    }

    public TimeEntry update(long id, TimeEntry timeEntry){
        timeEntry.setId(id);
        return timeEntry;

    }

    public void delete(long id){

    }

}

