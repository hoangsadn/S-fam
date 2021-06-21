package com.example.demo.amazon;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/image")
@CrossOrigin("*")
@AllArgsConstructor

public class ImageController {
    private final ImageService imageService;

    @PostMapping(
            path = "{email}/avt/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public void uploadUserProfileImage(@PathVariable("email") String email,
                                       @RequestParam("file") MultipartFile file) {
        imageService.uploadUserProfileImage(email, file);
    }

    @GetMapping(path = "{email}/avt/download",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadUserProfileImage(@PathVariable("email") String email) {
        return imageService.downloadUserProfileImage(email);
    }

    @GetMapping(path = "/download",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadImage(@RequestParam("path") String path,@RequestParam("name") String name) {
        return imageService.downloadImage(path,name);

    }

    @PostMapping(
            path = "album/{album}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadAlbumImages(@PathVariable("album") Long id,
                                        @RequestParam("files") MultipartFile[] files) {
        imageService.uploadAlbumImages(id, files);
    }

    @PostMapping(
            path = "item/{item}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadItemImage(@PathVariable("item") Long id,
                                        @RequestParam("file") MultipartFile file) {
        imageService.uploadItemImage(id, file);
    }
}
