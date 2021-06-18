package com.example.demo.personSchedule;

import com.example.demo.event.Event;
import com.example.demo.event.EventRepository;
import com.example.demo.event.EventRequest;
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
public class PersonScheduleService {
    private final PersonScheduleRepository personScheduleRepository;
    private final AppUserService appUserService;
    private Set<AppUser> appUserSet ;

    @Transactional
    public String createPersonSchedule(EventRequest request) {
        appUserSet = new HashSet<>();
        for (String email : request.getAppUserSet()){
            appUserSet.add(appUserService.findAppUserByEmail(email).get());
        }

        PersonSchedule personSchedule = new PersonSchedule(request.getName(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime(),
                request.getRepeatType(),
                request.getDetail());

        personScheduleRepository.save(personSchedule);

        return "create person schedule success";
    }

    @Transactional
    public String delPersonSchedule(Long id){

        Optional<PersonSchedule> personSchedule = personScheduleRepository.findById(id);
        if (!personSchedule.isPresent())
        {
            return "id not found";
        }
        personScheduleRepository.delete(personSchedule.get());
        return "delete person schedule success";
    }


    @Transactional
    public String editPersonSchedule(Long id, EventRequest request) {

        appUserSet = new HashSet<>();
        for (String email : request.getAppUserSet()){
            appUserSet.add(appUserService.findAppUserByEmail(email).get());
        }

        Optional<PersonSchedule> personSchedule = personScheduleRepository.findById(id);
        personSchedule.get().setName(request.getName());
        personSchedule.get().setDay(request.getDay());
        personSchedule.get().setDetail(request.getDetail());
        personSchedule.get().setStartTime(request.getStartTime());
        personSchedule.get().setEndTime(request.getEndTime());

        if (!personSchedule.isPresent()) {
            return "id not found";
        }
        personScheduleRepository.save(personSchedule.get());

        return "edit success";
    }

    public PersonSchedule getPersonScheduleById(Long id) {
        return personScheduleRepository.findById(id).get();

    }
}
