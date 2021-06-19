package com.example.demo.album;

import com.example.demo.note.Note;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumService {
    private final AppUserService appUserService;
    private final AlbumRepository albumRepository;

    public String createAlbum(String email, String name) {
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Album album = new Album(name,appUser.get());

        albumRepository.save(album);

        return "create album success";

    }

    public void setSetImgUrl(Album album, List<String> imgUrl){
        album.setSetImg(imgUrl);
        albumRepository.save(album);
    }

    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }
}