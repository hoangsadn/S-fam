package com.example.demo.note;

import com.example.demo.schedule.Schedule;
import com.example.demo.schedule.ScheduleRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/note")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping(path="notes")
    public List<Note> getSchedules(){
        return noteService.getNotes();
    }

    @PostMapping(path = "{email}/create")
    public String createSchedule(@PathVariable("email") String email,
                                 @RequestBody NoteRequest request){
        return noteService.createNote(email,request);
    }

    @GetMapping(path = "delete/{id}")
    public String delSchedule(@PathVariable("id") Long id){
        return noteService.delNote(id);
    }

    @GetMapping(path="{id}")
    public Note getNoteById(@PathVariable("id") Long id){
        return noteService.getNoteById(id);
    }

    @PutMapping(path = "{email}/edit/{id}")
    public String editSchedule(@PathVariable("email")String email, @PathVariable("id") Long id,@RequestBody NoteRequest request) {
        return noteService.editNote(email,id,request);
    }

}
