package com.example.demo.event;

import com.example.demo.SpringBootPushNotification.model.PushNotificationRequest;
import com.example.demo.SpringBootPushNotification.service.PushNotificationService;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        List<String> listToken = new ArrayList<>();
        for (String email : request.getAppUserSet()) {
            AppUser appUser = appUserService.findAppUserByEmail(email).get();
            appUserSet.add(appUser);
            listToken.add(appUser.getFirebaseToken());

        }
        request.setListToken(listToken);

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
            LocalDateTime date = convertToLocalDateTime(request.getDay());
            LocalTime time = convertToLocalTime(request.getStartTime());
            if (request.getEventRemindType() != EventRemindType.NONE)
            {
                time = time.minusMinutes(request.getRemindNum());
            }

            log.info(date +" "+ time);

            String minutes = String.valueOf(time.getMinute());
            String Hour = String.valueOf(time.getHour());
            String Day = String.valueOf(date.getDayOfMonth());
            String Month = String.valueOf(date.getMonthValue());
            String Year = String.valueOf(date.getYear());

            log.info("0 " + minutes + " " + Hour + " " + Day + " " + Month + " *");
            CronTrigger cronTrigger
                    = new CronTrigger("0 " + minutes + " " + Hour + " " + Day + " " + Month + " *");


            taskScheduler.schedule(RunnableTask(request), cronTrigger);

            //Repeat , do latter
            switch (request.getRepeatType()) {
                case WEEK:

                case MONTH:

                case YEAR:

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
                request.setListToken(eventRequest.getListToken());

                log.info("runable ", request);

                Map<String, String> map = new HashMap<>();
                map.put("type","event");
                map.put("id",eventRequest.getId().toString());

                pushNotificationService.sendPushNotificationCustomDataWithManyToken(map,request);
            }

        };
    }

    public LocalDateTime convertToLocalDateTime(String date){
        date = date.replace(" ","T");
        return LocalDateTime.parse(date);
    }

    public LocalTime convertToLocalTime(String time){
        return LocalTime.parse(time);
    }
}