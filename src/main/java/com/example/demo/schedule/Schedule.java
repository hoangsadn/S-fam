package com.example.demo.personSchedule;

import com.example.demo.event.EventRemindType;
import com.example.demo.event.EventRepeatType;
import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class PersonSchedule {


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
    private Date day;
    private Date startTime;
    private Date endTime;
    private EventRepeatType repeatType;

    public PersonSchedule(String name, Date day, Date startTime, Date endTime, EventRepeatType repeatType,
                  String detail) {
        this.name = name;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeatType = repeatType;
        this.detail = detail;
        this.timeCreateEvent = LocalDate.now();
    }



    private String detail;
    private LocalDate timeCreateEvent;


}
