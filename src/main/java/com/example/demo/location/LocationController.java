package com.example.demo.location;

import com.example.demo.note.Note;
import com.example.demo.note.NoteRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/location")
@AllArgsConstructor

public class LocationController {
    private final LocationService locationService;

    @PutMapping(path = "{email}/update")
    public String updateLocation(@PathVariable("email")String email, @RequestBody LocationRequest request) {
        return locationService.updateLocation(email,request);
    }

    @GetMapping(path="{email}")
    public Location getNoteByEmail(@PathVariable("email") String email){
        return locationService.getLocationByEmail(email);
    }
}
