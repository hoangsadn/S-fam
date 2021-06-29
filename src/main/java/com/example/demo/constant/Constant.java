package com.example.demo.constant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Constant {
    public static final Set<String> JSON_IGNORE_PROPERTIES_VALUE_APP_USER =
            Collections.unmodifiableSet (
                    new HashSet<String>(
                            Arrays.asList("sssss","ssss")));

    public static final String ADAD = "sssss";


}
