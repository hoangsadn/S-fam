package com.example.demo.user;


import com.example.demo.userlogin.UserLogin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    public AppUser(String email, String fullName, String gender, Date dob,UserLogin userLogin) {
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.userLogin = userLogin;

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
    private String email;



//    @ManyToOne
//    @JoinColumn(name = "family_id")
//    private Family family;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_login",referencedColumnName = "email")
    private UserLogin userLogin;


}
