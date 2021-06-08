package com.example.demo.event;

import com.example.demo.user.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Event {
    public Event(String name, Set<AppUser> appUserSet, Date startTime, Date endTime, Date day, Date timeCreateEvent, String detail) {
        this.name = name;
        this.appUserSet = appUserSet;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.timeCreateEvent = timeCreateEvent;
        Detail = detail;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="event_user",
            joinColumns = @JoinColumn(name="event_id"),
    inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    private Set<AppUser> appUserSet;


    private Date startTime;
    private Date endTime;
    private Date day;
    private Date timeCreateEvent;
    private String Detail;

}
