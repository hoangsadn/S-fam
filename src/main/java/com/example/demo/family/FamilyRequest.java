package com.example.demo.family;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FamilyRequest {
    private final String familyName;
    private final String userName;
    private final String key;
}
