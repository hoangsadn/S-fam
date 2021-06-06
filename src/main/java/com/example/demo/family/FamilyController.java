package com.example.demo.family;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

@RequestMapping(path="api/v1/family")
public class FamilyController {
    private final FamilyService familyService;

    @PostMapping(path = "create")
    public String createFamily(@RequestBody FamilyRequest familyRequest){
        return familyService.createFamily(familyRequest);
    }
//    @PostMapping(path = "create")
//    public String createFamily(@RequestParam(value = "id") Long id){
//        return familyService.createFamily(id);
//    }

//    @PostMapping(path ="join")
//    public String joinFamily(@RequestBody FamilyRequest familyRequest){
//        return familyService.joinFamily(familyRequest);
//    }
}
