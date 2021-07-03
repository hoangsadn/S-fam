package com.example.demo.schedule;

import com.example.demo.SpringBootPushNotification.model.PushNotificationRequest;
import com.example.demo.SpringBootPushNotification.service.PushNotificationService;
import com.example.demo.event.EventRemindType;
import com.example.demo.event.EventRequest;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AppUserService appUserService;
    private PushNotificationService pushNotificationService;

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    @Transactional
    public String createPersonSchedule(String email, ScheduleRequest request) {

        Optional <AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Schedule schedule = new Schedule(appUser.get(),request.getName(),
                request.getStartDay(),
                request.getEndDay(),
                request.getStartTime(),
                request.getEndTime(),
                request.getRepeatType(),
                request.getDetail());

        scheduleRepository.save(schedule);
        request.setId(schedule.getId());
        request.setToken(appUser.get().getFirebaseToken());

        LocalDateTime date = convertToLocalDateTime(request.getStartDay());
        LocalTime time = convertToLocalTime(request.getStartTime());


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


        return "create person schedule success";
    }

    @Transactional
    public String delPersonSchedule(Long id){

        Optional<Schedule> personSchedule = scheduleRepository.findById(id);
        if (!personSchedule.isPresent())
        {
            return "id not found";
        }
        scheduleRepository.delete(personSchedule.get());
        return "delete person schedule success";
    }


    @Transactional
    public String editPersonSchedule(String email, Long id, ScheduleRequest request) {
        Optional <AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Optional<Schedule> personSchedule = scheduleRepository.findByIdAndAppUserScheduleId(id,appUser.get().getId());
        if (!personSchedule.isPresent()) {
            return "id and app user not found";
        }
        personSchedule.get().setName(request.getName());
        personSchedule.get().setStartDay(request.getStartDay());
        personSchedule.get().setEndDay(request.getEndDay());
        personSchedule.get().setDetail(request.getDetail());
        personSchedule.get().setStartTime(request.getStartTime());
        personSchedule.get().setEndTime(request.getEndTime());


        scheduleRepository.save(personSchedule.get());

        return "edit success";
    }

    public Schedule getPersonScheduleById(Long id) {
        return scheduleRepository.findById(id).get();

    }

    public List<Schedule> getPersonSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getPersonSchedulesByEmail(String email) {
        return scheduleRepository.findAllByAppUserSchedule_Email(email);
    }


    public Runnable RunnableTask(ScheduleRequest eventRequest) {

        return new Runnable() {

            @Override
            public void run() {
                PushNotificationRequest request = new PushNotificationRequest();
                request.setTitle(eventRequest.getName());
                request.setMessage(eventRequest.getDetail());
                request.setToken(eventRequest.getToken());

                log.info("runable schedule ", request);

                Map<String, String> map = new HashMap<>();
                map.put("type","schedule");
                map.put("id",eventRequest.getId().toString());

                pushNotificationService.sendPushNotificationToTokenWithData(map,request);
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
