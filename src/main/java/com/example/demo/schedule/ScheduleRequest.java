package com.example.demo.schedule;

import com.example.demo.event.EventRemindType;
import com.example.demo.event.EventRepeatType;
import com.example.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScheduleRequest {
    private final AppUser appUser;
    private final String name;
    private final String startTime;
    private final String endTime;
    private final String startDay;
    private final String endDay;
    private final String detail;
    private final Set<ScheduleRepeatType> repeatType;
}
