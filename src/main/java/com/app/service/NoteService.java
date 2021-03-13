package com.app.service;

import com.app.exceptions.AuthException;
import com.app.exceptions.NotFoundException;
import com.app.model.Note;
import com.app.model.User;
import com.app.repository.NoteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepo noteRepo;
    private final AuthService authService;

    public void saveNote(Note noteItem, Principal principal){
        User currentUser = authService.findLoggedInUser(principal);
        Note note = new Note(currentUser, noteItem.getTitle(), noteItem.getDescription());

        noteRepo.save(note);
    }

    @Transactional(readOnly = true)
    public List<Note> getAllNotes(Principal principal) {
        return authService.findLoggedInUser(principal).getNotes();
    }


    public void deleteNote(Long id, Principal principal){
        boolean noteExists = noteRepo.existsById(id);
        User currentUser = authService.findLoggedInUser(principal);
        if(!noteExists){
            throw new NotFoundException("Not found");
        }
        Note noteFromDb = noteRepo.findById(id).orElseThrow();

        if(noteFromDb.getUser() != currentUser){
            throw new AuthException("Unable to do it");
        }

        noteRepo.deleteById(id);
    }


}
