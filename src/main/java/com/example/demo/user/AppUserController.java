package com.example.demo.user;

import com.example.demo.amazon.ImageService;
import com.example.demo.registration.RegistrationRequest;
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

    @PutMapping(path="/{email}/edit/pin")
    public String editPinCode(@PathVariable String email,@RequestParam("pin") String pinCode){
        return appUserService.editPinUser(email,pinCode);
    }

    @PutMapping(path="/{email}/edittoken")
    public String editFireBaseToken(@PathVariable String email,@RequestParam("fbtoken") String token){
        return appUserService.editFireBaseToken(email,token);
    }

    @PutMapping(path="/{email}/edit")
    public String editAppUser(@PathVariable String email, @RequestBody RegistrationRequest request){
        return  appUserService.editAppUserInfo(email,request);
    }



}
