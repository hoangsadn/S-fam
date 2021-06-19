package com.example.demo.album;

import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Album {
    public Album(String name, AppUser appUserAlbum) {
        this.name = name;
        this.appUserAlbum = appUserAlbum;
    }

    @SequenceGenerator(
            name = "album_sequence",
            sequenceName = "album_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "album_sequence"
    )
    private Long id;
    private String name;

    @ElementCollection
    private List<String> setImg;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            nullable = false,
            name = "app_user_id",
            referencedColumnName = "id"
    )
    private AppUser appUserAlbum;

}
