package com.example.demo.family;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
@AllArgsConstructor

//@RequestMapping(path="api/v1/family")
public class FamilyController {
    private final FamilyService familyService;

//    @PostMapping(path = "create")
//    public String createFamily(@RequestParam(value = "id") Long id, @RequestBody FamilyRequest familyRequest){
//        return familyService.createFamily(id,familyRequest);
//    }
//    @PostMapping(path = "create")
//    public String createFamily(@RequestParam(value = "id") Long id){
//        return familyService.createFamily(id);
//    }

    @PostMapping(path ="join")
    public String joinFamily(@RequestBody FamilyRequest familyRequest){
        return familyService.joinFamily(familyRequest);
    }
}
