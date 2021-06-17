package com.example.demo.user;


import com.example.demo.event.Event;
import com.example.demo.family.Family;
import com.example.demo.userlogin.UserLogin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements Serializable {

    public AppUser(String email, String fullName, String gender, Date dob,UserLogin userLogin) {
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.userLogin = userLogin;

    }

    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 10
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;

    private String fullName;
    private String gender;
    private Date dob;
    private String pinCode;
    private String imgUrl;
    private String email;

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(imgUrl);
    }

    @ManyToOne
    @JoinColumn(name = "family_id",referencedColumnName = "id")
    private Family family;


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