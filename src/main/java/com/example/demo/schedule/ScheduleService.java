package com.example.demo.schedule;

import com.example.demo.event.EventRequest;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AppUserService appUserService;

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
            return "id not found";
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

//    public List<Schedule> getPersonSchedulesByEmail(String email) {
//        Optional<AppUser> appUser =  appUserService.findAppUserByEmail(email);
//        if (!appUser.isPresent())
//            new IllegalStateException("not found user");
//        return scheduleRepository.findAllByAppUserSchedule(appUser.get());
//    }
}
