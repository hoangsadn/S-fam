package com.example.demo.schedule;

import com.example.demo.event.EventRepeatType;
import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class Schedule {


    @SequenceGenerator(
            name = "person_schedule_sequence",
            sequenceName = "event_sequence",
            allocationSize = 10
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_schedule_sequence"
    )

    private Long id;

    private String name;
    private Date startDay;
    private Date endDay;
    private Date startTime;
    private Date endTime;

    @ElementCollection
    private Set<ScheduleRepeatType> repeatType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            nullable = false,
            name = "app_user_id",
            referencedColumnName = "id"
    )
    private AppUser appUserSchedule;

    public Schedule(AppUser appUser,String name, Date startDay,Date endDay,
                    Date startTime, Date endTime,
                    Set<ScheduleRepeatType> repeatType,
                    String detail) {
        this.appUserSchedule = appUser;
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeatType = repeatType;
        this.detail = detail;
        this.timeCreateEvent = LocalDate.now();
    }

    private String detail;
    private LocalDate timeCreateEvent;


}
