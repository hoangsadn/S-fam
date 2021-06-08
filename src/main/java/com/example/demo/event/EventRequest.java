package com.example.demo.event;

import com.example.demo.user.AppUser;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

public class EventRequest {
    private String name;
    private Set<AppUser> appUserSet;
    private Date startTime;
    private Date endTime;
    private String Detail;
}
