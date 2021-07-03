package com.example.demo.warning;

import com.example.demo.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/warning")
@AllArgsConstructor
public class WarningController {
    private final WarningService warningService;

    @GetMapping(path="{email}")
    public String sendWarningNotification(@PathVariable("email") String email){
        return warningService.sendWarningNotification(email);
    }

}
