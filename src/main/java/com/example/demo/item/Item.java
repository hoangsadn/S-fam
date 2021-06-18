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
    public Item(String name, String detail, AppUser appUserItem,String path,String imgName) {
        this.name = name;
        this.detail = detail;
        this.appUserItem = appUserItem;
        this.path = path;
        this.imgName = imgName;

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
    private String path;
    private String imgName;

    @ManyToOne
    @JsonIgnoreProperties({"fullName","gender","dob","pinCode",
            "family","schedules","userLogin","eventSet","appUserSchedule"
            ,"notes","appUserNote",
            "items","appUserItem"})
    @JoinColumn(
            nullable = false,
            name = "app_user_id",
            referencedColumnName = "id"
    )
    private AppUser appUserItem;


}
