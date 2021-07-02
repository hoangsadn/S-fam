package com.example.demo.schedule;

import com.example.demo.event.EventRemindType;
import com.example.demo.event.EventRepeatType;
import com.example.demo.user.AppUser;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScheduleRequest {
    private final AppUser appUser;
    private Long id;
    private String token;
    private final String name;
    private final String startTime;
    private final String endTime;
    private final String startDay;
    private final String endDay;
    private final String detail;
    private final Set<ScheduleRepeatType> repeatType;
}
