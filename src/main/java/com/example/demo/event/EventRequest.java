package com.example.demo.event;

import com.example.demo.user.AppUser;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EventRequest {
    private final String name;
    private Long id;
    private final Set<String> appUserSet;
    private List<String> listToken;
    private final String startTime;
    private final String endTime;
    private final String day;
    private final String detail;
    private final EventRepeatType repeatType;
    private final Integer remindNum;
    private final EventRemindType eventRemindType;
}
