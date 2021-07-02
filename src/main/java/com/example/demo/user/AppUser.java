package com.example.demo.user;


import com.example.demo.album.Album;
import com.example.demo.event.Event;
import com.example.demo.family.Family;
import com.example.demo.item.Item;
import com.example.demo.note.Note;
import com.example.demo.schedule.Schedule;
import com.example.demo.userlogin.UserLogin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements Serializable {

    public AppUser(String email, String fullName,String phoneNumber, String gender, String dob,UserLogin userLogin) {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.userLogin = userLogin;

    }

    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;

    private String fullName;
    private String gender;
    private String dob;
    private String pinCode;
    private String imgUrl;
    private String email;
    private String phoneNumber;
    @JsonIgnore
    private String firebaseToken;


    @JsonIgnore
    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(imgUrl);
    }

    @ManyToOne
    @JoinColumn(name = "family_id",referencedColumnName = "id")
    private Family family;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "appUserSchedule")
    //@JsonIgnoreProperties({"app_user"})
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "appUserNote")
    private Set<Note> notes  = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "appUserItem")
    private Set<Item> items  = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "appUserAlbum")
    private Set<Album> albums  = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_login",referencedColumnName = "email")
    private UserLogin userLogin;

//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name= "event_user",
//        joinColumns = @JoinColumn(name="app_user_id"),
//        inverseJoinColumns = @JoinColumn(name="event_id"))


    @ManyToMany(mappedBy = "appUserSet")
    Set<Event> eventSet = new HashSet<>();


}