package com.example.demo.user;

import com.example.demo.amazon.BucketName;
import com.example.demo.amazon.FileStore;
import com.example.demo.amazon.ImageService;
import com.example.demo.family.Family;
import com.example.demo.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Log4j2
@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public Optional<AppUser> findAppUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }

    public void insertFamily(AppUser appUser, Family family){
        appUser.setFamily(family);
        appUserRepository.save(appUser);
    }


    public AppUser getInfoUser(String email){
        AppUser appUser = appUserRepository.findAppUserByEmail(email).get();
        return appUser;
    }

    public void setImgUrl(AppUser appUser,String imgUrl){
        appUser.setImgUrl(imgUrl);
        appUserRepository.save(appUser);
    }

    public String editPinUser(String email, String pinCode) {
        Optional<AppUser> appUser = appUserRepository.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return "email not found";
        }
        appUser.get().setPinCode(pinCode);
        appUserRepository.save(appUser.get());
        return "edit success";
    }

    public String editAppUserInfo(String email, RegistrationRequest request) {
        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByEmail(email);
        if (!appUserOptional.isPresent())
        {
            return "email not found";
        }
        AppUser appUser = appUserOptional.get();
        appUser.setFullName(request.getFullName());
        appUser.setPhoneNumber(request.getPhoneNumber());
        appUser.setGender(request.getGender());
        appUser.setDob(request.getDob());
        appUserRepository.save(appUser);
        return "edit success";
    }

    public String editFireBaseToken(String email, String token) {
        Optional<AppUser> appUser = appUserRepository.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return "email not found";
        }
        appUser.get().setFirebaseToken(token);
        appUserRepository.save(appUser.get());
        return "edit success";
    }
}
