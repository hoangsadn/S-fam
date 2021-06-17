package com.example.demo.amazon;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/image")
@CrossOrigin("*")
@AllArgsConstructor

public class ImageController {
    private final ImageService imageService;

    @PostMapping(
            path = "{email}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public void uploadUserProfileImage(@PathVariable("email") String email,
                                       @RequestParam("file") MultipartFile file) {
        imageService.uploadUserProfileImage(email, file);
    }

    @GetMapping(path = "{userProfileId}/image/download",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") String email) {
        return imageService.downloadUserProfileImage(email);
    }
}
