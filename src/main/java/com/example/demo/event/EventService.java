package com.example.demo.event;

import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AppUserService appUserService;
    private Set<AppUser> appUserSet ;

    @Transactional
    public String createEvent(EventRequest request) {
        appUserSet = new HashSet<>();
        for (String email : request.getAppUserSet()){
            appUserSet.add(appUserService.findAppUserByEmail(email).get());
        }

        Event event = new Event(request.getName(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime(),
                appUserSet,
                request.getDetail());

        eventRepository.save(event);

        return "create event success";
    }

    @Transactional
    public String delEvent(Long id){

        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent())
        {
            return "id not found";
        }
        eventRepository.delete(event.get());
        return "delete event success";
    }


    @Transactional
    public String editEvent(Long id, EventRequest request) {

        appUserSet = new HashSet<>();
        for (String email : request.getAppUserSet()){
            appUserSet.add(appUserService.findAppUserByEmail(email).get());
        }

        Optional<Event> event = eventRepository.findById(id);
        event.get().setName(request.getName());
        event.get().setDay(request.getDay());
        event.get().setAppUserSet(appUserSet);
        event.get().setDetail(request.getDetail());
        event.get().setStartTime(request.getStartTime());
        event.get().setEndTime(request.getEndTime());

        if (!event.isPresent()) {
            return "id not found";
        }
        eventRepository.save(event.get());

        return "edit success";
    }
}
