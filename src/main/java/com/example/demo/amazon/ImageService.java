package com.example.demo.amazon;

import com.amazonaws.services.ecs.model.Resource;
import com.example.demo.album.Album;
import com.example.demo.album.AlbumService;
import com.example.demo.item.Item;
import com.example.demo.item.ItemService;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserRepository;
import com.example.demo.user.AppUserService;
import javassist.compiler.ast.Pair;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class ImageService {
    private final FileStore fileStore;
    private final AppUserService appUserService;
    private final ItemService itemService;
    private final AlbumService albumService;
    @Transactional
    public void uploadUserProfileImage(String email, MultipartFile file) {
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
        Map<String, String> metadata = new HashMap<>();
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

    @Transactional
    byte[] downloadUserProfileImage(String email) {
        AppUser appUser = appUserService.findAppUserByEmail(email).get();

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                email);

        return appUser.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    @Transactional
    byte[] downloadImage(String pathImg, String name) {

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),pathImg);

        return fileStore.download(path, name);
    }

    @Transactional
    public void uploadAlbumImages(Long id, MultipartFile[] files) {
        // 1. Check if image is not empty
        if (Arrays.stream(files).count()==0) {
            throw new IllegalStateException("Cannot upload empty file ");
        }
        // 2. If file is an image

        // 3. The user exists in our database
        Optional<Album> album = albumService.findById(id);

        // 4. Grab some metadata from file if any
        List<String> listFileName = new ArrayList<>();
        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        for (MultipartFile file : files) {
            String path = String.format("%s/%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),"album",id);
            String filename = String.format("%s", file.getOriginalFilename());
            try {
                fileStore.save(path, filename, file.getInputStream());
                listFileName.add(filename);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        albumService.setSetImgUrl(album.get(),listFileName);

    }

    @Transactional
    public void uploadItemImage(Long id, MultipartFile file) {
        String path = String.format("%s/%s","item",id);
        uploadImage(path,file);
        Optional<Item> optionalItem = itemService.findById(id);
        itemService.setImgName(id,String.format("%s", file.getOriginalFilename()));
    }

    @Transactional
    public void uploadImage(String pathImg, MultipartFile file){
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


        // 4. Grab some metadata from file if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));


        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),pathImg);
        //String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        String filename = String.format("%s", file.getOriginalFilename());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
