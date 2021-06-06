package com.example.demo.family;

import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserRepository;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

        String familyName = UUID.randomUUID().toString();

        boolean userExits =  appUserService.findAppUserByEmail(request.getUserName()).isPresent();
        if (!userExits)
        {
           return "user not exits";
        }
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(request.getUserName());

        Family family = new Family(request.getFamilyName(),
                request.getUserName(),
                appUser.get()
        );
        familyRepository.save(family);

        return familyName;

}
//
//    public String joinFamily(FamilyRequest request) {
//        boolean isFamilyExits = familyRepository.findByKey(request.getKey()).isPresent();
//        if (isFamilyExits) {
//            return "join success";
//        }
//        boolean userExits = appUserRepository.findAppUserByUserLogin(request.getUserName()).isPresent();
//        if (userExits) {
//            Optional<Family> family = familyRepository.findByKey(request.getKey());
//            //family.get().setName();
//            //familyRepository.save();
//        }
//        return "key not vaild";
//        // chưa làm ai tham gia
//
//    }
//
//    public String createFamily(Long id) {
//        return "ss";
//    }
}
