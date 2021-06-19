package com.example.demo.event;

import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Event {


    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 10
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )

    private Long id;

    private String name;
    private String day;
    private String startTime;
    private String endTime;
    private EventRepeatType repeatType;
    private Integer remindNum;
    private EventRemindType eventRemindType;

    public Event(String name, String day, String startTime, String endTime, EventRepeatType repeatType,
                 EventRemindType eventRemindType, Integer remindNum,
                 Set<AppUser> appUserSet, String detail) {
        this.name = name;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeatType = repeatType;
        this.remindNum = remindNum;
        this.eventRemindType = eventRemindType;
        this.appUserSet = appUserSet;
        this.detail = detail;
        this.timeCreateEvent = LocalDateTime.now();
    }

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="event_user",
            joinColumns = @JoinColumn(name="event_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    private Set<AppUser> appUserSet = new HashSet<>();

    private String detail;
    private LocalDateTime timeCreateEvent;



}
