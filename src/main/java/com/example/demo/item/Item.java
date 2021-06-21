package com.example.demo.item;

import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {
    public Item(String name, String detail, AppUser appUserItem) {
        this.name = name;
        this.detail = detail;
        this.appUserItem = appUserItem;
    }

    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )

    private Long id;

    private String name;
    private String detail;
    private String imgName;

    @ManyToOne
    @JsonIgnoreProperties({"fullName","gender","dob","pinCode","phoneNumber",
            "family","schedules","userLogin","eventSet","appUserSchedule"
            ,"notes","appUserNote",
            "items","appUserItem",
            "albums","appUserAlbum"})
    @JoinColumn(
            nullable = false,
            name = "app_user_id",
            referencedColumnName = "id"
    )
    private AppUser appUserItem;


}
