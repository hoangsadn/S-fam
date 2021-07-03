package com.example.demo.family;

import com.example.demo.album.Album;
import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @JsonIgnoreProperties({"fullName","gender","dob","pinCode","phoneNumber",
            "family","schedules","userLogin","eventSet","appUserSchedule"
            ,"notes","appUserNote",
            "items","appUserItem",
            "albums","appUserAlbum"})
    @JoinColumn(name= "owner",referencedColumnName = "email")
    private AppUser owner;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "family")
    @JsonIgnoreProperties({"family","eventSet"})
    private Set<AppUser> memberSet = new HashSet<>();

    public void addMember(AppUser appUser){
        memberSet.add(appUser);
    }


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "familyAlbum")
    @JsonIgnore
    private Set<Album> albums  = new HashSet<>();

}
