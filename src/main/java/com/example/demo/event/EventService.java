package com.example.demo.event;

import com.example.demo.SpringBootPushNotification.model.PushNotificationRequest;
import com.example.demo.SpringBootPushNotification.service.PushNotificationService;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Log4j2
public class EventService {
    private final EventRepository eventRepository;
    private final AppUserService appUserService;
    private Set<AppUser> appUserSet;
    private PushNotificationService pushNotificationService;

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    @Transactional
    public String createEvent(EventRequest request) {
        appUserSet = new HashSet<>();
        for (String email : request.getAppUserSet()) {
            appUserSet.add(appUserService.findAppUserByEmail(email).get());
        }

        Event event = new Event(request.getName(),
                request.getDay(),
                request.getStartTime(),
                request.getEndTime(),
                request.getRepeatType(),
                request.getEventRemindType(),
                request.getRemindNum(),
                appUserSet,
                request.getDetail());

        eventRepository.save(event);
        request.setId(event.getId());

        if (request.getEventRemindType() != EventRemindType.NONE) {
            LocalDateTime date = LocalDateTime.now().plusMinutes(1);
            log.info(date);

            String minutes = String.valueOf(date.getMinute());
            String Hour = String.valueOf(date.getHour());
            String Day = String.valueOf(date.getDayOfMonth());
            String Month = String.valueOf(date.getMonthValue());


            log.info("0 " + minutes + " " + Hour + " " + Day + " " + Month + " *");
            CronTrigger cronTrigger
                    = new CronTrigger("0 " + minutes + " " + Hour + " " + Day + " " + Month + " *");

            taskScheduler.schedule(RunnableTask(request), cronTrigger);
            log.info("create event");

            switch (request.getEventRemindType()) {
                case MINUTE:

                case HOUR:

                case DAY:
            }
        }


        return "create event success";
    }

    @Transactional
    public String delEvent(Long id) {

        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            return "id not found";
        }
        eventRepository.delete(event.get());
        return "delete event success";
    }


    @Transactional
    public String editEvent(Long id, EventRequest request) {

        appUserSet = new HashSet<>();
        for (String email : request.getAppUserSet()) {
            appUserSet.add(appUserService.findAppUserByEmail(email).get());
        }

        Optional<Event> event = eventRepository.findById(id);
        event.get().setName(request.getName());
        event.get().setDay(request.getDay());
        event.get().setAppUserSet(appUserSet);
        event.get().setDetail(request.getDetail());
        event.get().setRepeatType(request.getRepeatType());
        event.get().setEventRemindType(request.getEventRemindType());
        event.get().setRemindNum(request.getRemindNum());
        event.get().setStartTime(request.getStartTime());
        event.get().setEndTime(request.getEndTime());

        if (!event.isPresent()) {
            return "id not found";
        }
        eventRepository.save(event.get());

        return "edit success";
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }


    public Runnable RunnableTask(EventRequest eventRequest) {

        return new Runnable() {

            @Override
            public void run() {
                PushNotificationRequest request = new PushNotificationRequest();
                request.setTitle(eventRequest.getName());
                request.setMessage(eventRequest.getDetail());
                request.setToken("dNKp3kH2OrE:APA91bHpVmwMFRnMkBKkZFfo_z96B63DK3ka-1-4i2bAQYmWfnBlHvJktFB8vCkxTwVWAU9fSjfSIvq0v3q4n68aFgMqhXhEZlqF7sXgCprm3kK_oHepsq75nOjU8bHB-MKoY-js9mqg");
                request.setTopic("global");

                log.info("runable ", request);

                Map<String, String> map = new HashMap<>();
                map.put("id",eventRequest.getId().toString());

                pushNotificationService.sendPushNotificationCustomDataWithTopic(map,request);
            }

        };
    }
}