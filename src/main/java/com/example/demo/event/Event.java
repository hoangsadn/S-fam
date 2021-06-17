package com.example.demo.event;

import com.example.demo.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Date day;
    private Date startTime;
    private Date endTime;


    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name="event_user",
            joinColumns = @JoinColumn(name="event_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    private Set<AppUser> appUserSet = new HashSet<>();

    private String detail;
    private Date timeCreateEvent;

    public Event(String name, Date day, Date startTime, Date endTime,Set<AppUser> appUserSet,  String detail) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.appUserSet = appUserSet;
        this.detail = detail;
    }

    public Event(String name, Date day, Date startTime, Date endTime,  String detail) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.detail = detail;
    }


}
