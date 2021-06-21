package com.example.demo.album;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path="{id}")
    public Album getAlbumById(@PathVariable("id") Long id){
        return albumService.getAlbumById(id);
    }

    @GetMapping(path="albums")
    public List<Album> getAlbums(){
        return albumService.getAlbums();
    }
    @PutMapping(path="{id}/edit")
    public String getAlbums(@PathVariable("id") Long id,@RequestBody AlbumRequest request){
        return albumService.editAlbum(id,request);
    }

    @DeleteMapping(path = "delete/{id}")
    public String delAlbum(@PathVariable("id") Long id){
        return albumService.delAlbum(id);
    }

}
