package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    @PostMapping(path = "create")
    public String createEvent(@RequestBody EventRequest eventRequest){
        return eventService.createEvent(eventRequest);
    }
}
