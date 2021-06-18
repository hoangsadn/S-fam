package com.example.demo.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/note")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(path="notes")
    public List<Item> getSchedules(){
        return itemService.getNotes();
    }

    @PostMapping(path = "{email}/create")
    public String createSchedule(@PathVariable("email") String email,
                                 @RequestBody ItemRequest request){
        return itemService.createNote(email,request);
    }

    @GetMapping(path = "delete/{id}")
    public String delSchedule(@PathVariable("id") Long id){
        return itemService.delNote(id);
    }

    @GetMapping(path="{id}")
    public Item getNoteById(@PathVariable("id") Long id){
        return itemService.getNoteById(id);
    }

    @PutMapping(path = "{email}/edit/{id}")
    public String editSchedule(@PathVariable("email")String email, @PathVariable("id") Long id,@RequestBody ItemRequest request) {
        return itemService.editNote(email,id,request);
    }

}
