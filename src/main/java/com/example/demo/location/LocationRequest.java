package com.example.demo.location;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LocationRequest {
    private final Long id;

    private final String latitude;
    private final String longitude;
}
