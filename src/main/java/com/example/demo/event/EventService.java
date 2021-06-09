package com.example.demo.event;

import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AppUserService appUserService;
    private final Set<AppUser> appUserSet ;
    private final EntityManager entityManager;
    public String createEvent(EventRequest request) {
        //Event event = objectMapper.readValue(request,Event.class);


//        for (String email : request.getAppUserSet()){
//            appUserSet.add(appUserService.findAppUserByEmail(email).get());
//        }

        Event event = new Event(request.getName(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime(),
        //        appUserSet,
                request.getDetail());

        eventRepository.save(event);
       // entityManager.clear();
        return "ok";
    }
}
