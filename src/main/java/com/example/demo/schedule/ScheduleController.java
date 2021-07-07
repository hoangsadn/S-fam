package com.example.demo.schedule;

import com.example.demo.event.EventRequest;
import com.example.demo.userlogin.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/schedule")
@AllArgsConstructor
@CrossOrigin
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping()
    public List<Schedule> getSchedules(){
        return scheduleService.getPersonSchedules();
    }

    @GetMapping(path="{id}")
    public Schedule getScheduleById(@PathVariable("id") Long id){
        return scheduleService.getPersonScheduleById(id);
    }

    @PostMapping(path = "{email}/create")
    public String createSchedule(@PathVariable("email") String email,
                                 @RequestBody ScheduleRequest scheduleRequest){
        return scheduleService.createPersonSchedule(email,scheduleRequest);
    }

    @DeleteMapping(path = "delete/{id}")
    public String delSchedule(@PathVariable("id") Long id){
        return scheduleService.delPersonSchedule(id);
    }

    @PutMapping(path = "{email}/edit/{id}")
    public String editSchedule(@PathVariable("email")String email, @PathVariable("id") Long id,@RequestBody ScheduleRequest scheduleRequest) {
        return scheduleService.editPersonSchedule(email,id,scheduleRequest);
    }

    @GetMapping(path="email/{email}")
    public List<Schedule> getSchedulesByEmail(@PathVariable("email") String email){
        return scheduleService.getPersonSchedulesByEmail(email);
    }



}
