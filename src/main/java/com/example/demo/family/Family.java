package com.example.demo.family;

import com.example.demo.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Family {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name= "app_user_id")
    private AppUser owner;


}
