package com.example.demo.note;

import com.example.demo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {


    Optional<Note> findByIdAndAppUserNoteId(Long id, Long appid);
}
