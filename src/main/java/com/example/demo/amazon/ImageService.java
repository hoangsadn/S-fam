package com.example.demo.amazon;

import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserRepository;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class ImageService {
    private final FileStore fileStore;
    private final AppUserService appUserService;

    void uploadUserProfileImage(String email, MultipartFile file) {
        // 1. Check if image is not empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
        // 2. If file is an image
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }

        // 3. The user exists in our database
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(email);

        // 4. Grab some metadata from file if any
        Map<String, String> metadata =  new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));


        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), email);
        //String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        String filename = String.format("%s", file.getOriginalFilename());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            appUserService.setImgUrl(appUser.get(),filename);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
    byte[] downloadUserProfileImage(String email) {
        AppUser appUser = appUserService.findAppUserByEmail(email).get();

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                email);

        return appUser.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }


}
