package com.example.demo.album;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AlbumRequest {
    private final String name;
    private final List<String> setImg;
}
