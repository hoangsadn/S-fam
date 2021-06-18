package com.example.demo.note;

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
public class Note {
    public Note(String name, String detail, AppUser appUserNote) {
        this.name = name;
        this.detail = detail;
        this.appUserNote = appUserNote;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            nullable = false,
            name = "app_user_id",
            referencedColumnName = "id"
    )
    private AppUser appUserNote;


}
