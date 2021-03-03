package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    this.timeEntryRepository= timeEntryRepository;

    }

    @PostMapping("time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        final TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        return ResponseEntity.created(null).body(timeEntry);
    }
@GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry;
        timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry);

    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = null;
        timeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (timeEntry==null){
        return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntrylist = null;
        timeEntrylist =  timeEntryRepository.list();
        return ResponseEntity.ok(timeEntrylist);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {

       timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }

}
