package com.example.demo.event;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping(path="{id}")
    public Event getEventById(@PathVariable("id") Long id){
        return eventService.getEventById(id);
    }

//    @GetMapping
//    public List<Event> getEventByEmail(@RequestParam("email")String email) throws IllegalAccessException {
//        return eventService.getEventByEmail(email);
//    }

    @GetMapping(path="events")
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @PostMapping(path = "create")
    public String createEvent(@RequestBody EventRequest eventRequest){
        return eventService.createEvent(eventRequest);
    }

    @DeleteMapping(path = "delete/{id}")
    public String delEvent(@PathVariable("id") Long id){
        return eventService.delEvent(id);
    }

    @PutMapping(path = "edit/{id}")
    public String editEvent(@PathVariable("id") Long id,@RequestBody EventRequest eventRequest) {
        return eventService.editEvent(id,eventRequest);
    }
}
