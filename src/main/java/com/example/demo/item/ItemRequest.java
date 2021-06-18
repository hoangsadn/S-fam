package com.example.demo.item;

import com.example.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ItemRequest {
    private final String name;
    private final String detail;
    private final AppUser appUser;
    private final String path;
    private final String imgName;

}
