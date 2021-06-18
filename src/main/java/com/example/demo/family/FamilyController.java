package com.example.demo.family;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping(path="api/v1/family")
public class FamilyController {
    private final FamilyService familyService;

    @GetMapping(path="{id}")
    public Family getFamilyById(@PathVariable("id") Long id){
        return familyService.getFamilyById(id);
    }

    @PostMapping(path = "create")
    public String createFamily(@RequestBody FamilyRequest familyRequest){
        return familyService.createFamily(familyRequest);
    }

    @PostMapping(path ="join")
    public String joinFamily(@RequestBody FamilyRequest familyRequest){
        return familyService.joinFamily(familyRequest);
    }
}
