package com.example.demo.family;

import com.example.demo.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final AppUserRepository appUserRepository;
//    public String createFamily(Long id,FamilyRequest request) {
//        boolean isFamilyExits = familyRepository.findByName(request.getFamilyName()).isPresent();
//        if (isFamilyExits) {
//            return ("family name exits");
//        }
//
//        String familyName = UUID.randomUUID().toString();
//
//        //Family family = new Family(request.getFamilyName(),request.getUserName(),familyName);
//        //familyRepository.save(family);
//
//        return familyName;
//
//}
//
    public String joinFamily(FamilyRequest request) {
        boolean isFamilyExits = familyRepository.findByKey(request.getKey()).isPresent();
        if (isFamilyExits) {
            return "join success";
        }
        boolean userExits = appUserRepository.findAppUserByUserLogin(request.getUserName()).isPresent();
        if (userExits) {
            Optional<Family> family = familyRepository.findByKey(request.getKey());
            //family.get().setName();
            //familyRepository.save();
        }
        return "key not vaild";
        // chưa làm ai tham gia

    }
//
//    public String createFamily(Long id) {
//        return "ss";
//    }
}
