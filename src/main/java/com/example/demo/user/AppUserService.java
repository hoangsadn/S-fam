package com.example.demo.user;

import com.example.demo.family.Family;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

}
