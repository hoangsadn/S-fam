package com.example.demo.family;

import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserRepository;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Log4j2
@AllArgsConstructor
@Service
public class FamilyService {
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final FamilyRepository familyRepository;
    public String createFamily(FamilyRequest request) {
        boolean isFamilyExits = familyRepository.findByName(request.getFamilyName()).isPresent();
        if (isFamilyExits) {
            return ("family name exits");
        }

        String familyKey = UUID.randomUUID().toString();

        boolean userExits =  appUserService.findAppUserByEmail(request.getUserName()).isPresent();
        if (!userExits)
        {

           return "user not exits";
        }
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(request.getUserName());

        Family family = new Family(request.getFamilyName(),
                familyKey,
                appUser.get()
        );
        familyRepository.save(family);
        appUserService.insertFamily(appUser.get(),family);


        return familyKey;

}

    public String joinFamily(FamilyRequest request) {

        Optional<Family> family = familyRepository.findByKey(request.getKey());
        if (!family.isPresent()) {
            return "key not found";
        }
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(request.getUserName());
        if (!appUser.isPresent()) {
            return "user not found";
        }
        appUserService.insertFamily(appUser.get(),family.get());
        return "join success";

    }


}
