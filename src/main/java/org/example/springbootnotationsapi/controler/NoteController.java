package org.example.springbootnotationsapi.controler;


import lombok.RequiredArgsConstructor;
import org.example.springbootnotationsapi.model.notes.Note;
import org.example.springbootnotationsapi.model.notes.dto.create.CreateNoteRequest;
import org.example.springbootnotationsapi.model.notes.dto.create.CreateNoteResponse;
import org.example.springbootnotationsapi.model.notes.dto.delete.DeleteNoteResponse;
import org.example.springbootnotationsapi.model.notes.dto.get.GetNoteResponse;
import org.example.springbootnotationsapi.model.notes.dto.update.UpdateNoteRequest;
import org.example.springbootnotationsapi.model.notes.dto.update.UpdateNoteResponse;
import org.example.springbootnotationsapi.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public CreateNoteResponse create(@RequestBody CreateNoteRequest request) {
        return noteService.create( request);
    }


    @PatchMapping
    public UpdateNoteResponse update(@RequestBody UpdateNoteRequest request) {
        return noteService.update(request);
    }

    @DeleteMapping
    public DeleteNoteResponse delete(@RequestParam(name = "id") long id) {
        return noteService.delete(id);
    }

    @GetMapping
    public GetNoteResponse getNotes() {
        return noteService.getNotes();
    }
}
