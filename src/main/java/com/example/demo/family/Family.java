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

    public Family(String name, String key, AppUser owner) {
        this.name = name;
        this.key = key;
        this.owner = owner;
    }

    @SequenceGenerator(
            name = "family_sequence",
            sequenceName = "family_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "family_sequence"
    )
    private Long id;

    private String name;
    private String key;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "owner",referencedColumnName = "email")
    private AppUser owner;


}
