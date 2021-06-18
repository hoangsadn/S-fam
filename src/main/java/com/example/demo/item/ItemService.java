package com.example.demo.item;

import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final AppUserService appUserService;
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

}
