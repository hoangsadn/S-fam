package com.example.demo.event;

import com.example.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EventRequest {
    private final String name;
    private final Set<String> appUserSet;
    private final Date startTime;
    private final Date endTime;
    private final Date day;
    private final String detail;
}
