package com.example.demo.note;

import com.example.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NoteRequest {
    private final String name;
    private final String detail;
    private final AppUser appUser;

}
