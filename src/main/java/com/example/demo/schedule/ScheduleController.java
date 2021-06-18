package com.example.demo.personSchedule;

import com.example.demo.event.Event;
import com.example.demo.event.EventRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/schedule")
@AllArgsConstructor
public class PersonScheduleController {
    private final PersonScheduleService scheduleService;
    @GetMapping(path="{id}")
    public PersonSchedule getEventById(@PathVariable("id") Long id){
        return scheduleService.getPersonScheduleById(id);
    }

    @PostMapping(path = "create")
    public String createEvent(@RequestBody EventRequest eventRequest){
        return scheduleService.createPersonSchedule(eventRequest);
    }

    @GetMapping(path = "delete/{id}")
    public String delEvent(@PathVariable("id") Long id){
        return scheduleService.delPersonSchedule(id);
    }

    @PutMapping(path = "edit/{id}")
    public String editEvent(@PathVariable("id") Long id,@RequestBody EventRequest eventRequest) {
        return personSchedule.editEvent(id,eventRequest);
    }
}
