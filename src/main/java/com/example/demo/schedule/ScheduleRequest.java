package com.example.demo.schedule;

import com.example.demo.event.EventRemindType;
import com.example.demo.event.EventRepeatType;
import com.example.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScheduleRequest {
    private final AppUser appUser;
    private final String name;
    private final Date startTime;
    private final Date endTime;
    private final Date startDay;
    private final Date endDay;
    private final String detail;
    private final Set<ScheduleRepeatType> repeatType;
}
