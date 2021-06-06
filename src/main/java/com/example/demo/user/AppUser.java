package com.example.demo.user;


import com.example.demo.userlogin.UserLogin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    public AppUser(String fullName, String gender, Date dob) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
    }

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String fullName;
    private String gender;
    private Date dob;
    private String pinCode;
    private String imgUrl;



//    @ManyToOne
//    @JoinColumn(name = "family_id")
//    private Family family;



}
