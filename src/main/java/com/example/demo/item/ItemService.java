package com.example.demo.item;

import com.example.demo.amazon.BucketName;
import com.example.demo.amazon.FileStore;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final AppUserService appUserService;
    private final FileStore fileStore;
    public List<Item> getNotes() {
        return itemRepository.findAll();
    }

    public Item getNoteById(Long id) {
        return itemRepository.findById(id).get();

    }

    @Transactional
    public String createNote(String email, ItemRequest request) {
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Item item = new Item(request.getName(),
                request.getDetail(),appUser.get(),
                request.getPath(),
                request.getImgName());

        itemRepository.save(item);

        return "create success";
    }
    @Transactional
    public String delNote(Long id){

        Optional<Item> note = itemRepository.findById(id);
        if (!note.isPresent())
        {
            return "id not found";
        }
        itemRepository.delete(note.get());
        return "delete  success";
    }

    @Transactional
    public String editNote(String email, Long id, ItemRequest request) {
        Optional <AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Optional<Item> note = itemRepository.findByIdAndAppUserItemId(id,appUser.get().getId());
        if (!note.isPresent()) {
            return "id and app user not found";
        }
        note.get().setName(request.getName());
        note.get().setDetail(request.getDetail());


        itemRepository.save(note.get());

        return "edit success";
    }

    public String uploadUserProfileImage(String email, ItemRequest request, MultipartFile file) {
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

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Item item = new Item(request.getName(),
                request.getDetail(),appUser.get(),
                email,
                filename);

        itemRepository.save(item);

        return "create success";

    }

}
