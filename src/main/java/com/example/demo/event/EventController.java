package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    @PostMapping(path = "create")
    public String createEvent(@RequestBody EventRequest eventRequest){
        return eventService.createEvent(eventRequest);
    }

    @GetMapping(path = "delete/{id}")
    public String delEvent(@PathVariable("id") Long id){
        return eventService.delEvent(id);
    }

    @PutMapping(path = "edit/{id}")
    public String editEvent(@PathVariable("id") Long id,@RequestBody EventRequest eventRequest) {
        return eventService.editEvent(id,eventRequest);
    }
}
