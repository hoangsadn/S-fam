package com.example.demo.album;

import com.example.demo.note.NoteRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/album")
@AllArgsConstructor
public class AlbumController {
    AlbumService albumService;

    @PostMapping(path = "{email}/create")
    public String createAlbum(@PathVariable("email") String email,
                                 @RequestParam("name") String name){
        return albumService.createAlbum(email,name);
    }
    @PostMapping(path = "{email}/create")
    public String createAlbum(@PathVariable("email") String email,
                              @RequestParam("name") String name){
        return albumService.createAlbum(email,name);
    }
}
