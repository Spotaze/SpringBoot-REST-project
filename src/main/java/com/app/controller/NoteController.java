package com.app.controller;

import com.app.model.Note;
import com.app.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/note")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note, Principal principal){
        noteService.saveNote(note, principal);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Note>> getAllNotes(Principal principal){
        return status(HttpStatus.OK).body(noteService.getAllNotes(principal));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id, Principal principal){
        noteService.deleteNote(id, principal);
        return new ResponseEntity<>("Note deleted", HttpStatus.GONE);
    }
}
