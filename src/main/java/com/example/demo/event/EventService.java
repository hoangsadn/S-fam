package com.example.demo.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    public String createEvent(EventRequest request) {
        return "ok";
    }
}
