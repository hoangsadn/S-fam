package com.example.demo.note;

import com.example.demo.schedule.Schedule;
import com.example.demo.schedule.ScheduleRequest;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final AppUserService appUserService;
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).get();

    }

    @Transactional
    public String createNote(String email, NoteRequest request) {
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Note note = new Note(request.getName(),
                request.getDetail(),appUser.get());

        noteRepository.save(note);

        return "create note success";
    }
    @Transactional
    public String delNote(Long id){

        Optional<Note> note = noteRepository.findById(id);
        if (!note.isPresent())
        {
            return "id not found";
        }
        noteRepository.delete(note.get());
        return "delete note success";
    }

    @Transactional
    public String editNote(String email, Long id, NoteRequest request) {
        Optional <AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Optional<Note> note = noteRepository.findByIdAndAppUserNoteId(id,appUser.get().getId());
        if (!note.isPresent()) {
            return "id and app user not found";
        }
        note.get().setName(request.getName());
        note.get().setDetail(request.getDetail());


        noteRepository.save(note.get());

        return "edit success";
    }

    public List<Note> searchByName(String name, Long id) {
       return noteRepository.findAllByAppUserNoteIdAndNameIsContaining(id,name);
    }

    public List<Note> getNotesByEmail(String email) {
        return noteRepository.findAllByAppUserNote_Email(email);
    }
}
