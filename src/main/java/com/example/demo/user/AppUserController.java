package com.example.demo.user;

import com.example.demo.amazon.ImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping(path="/{email}")
    public AppUser getInfoUser(@PathVariable String email){
        return appUserService.getInfoUser(email);
    }
}
