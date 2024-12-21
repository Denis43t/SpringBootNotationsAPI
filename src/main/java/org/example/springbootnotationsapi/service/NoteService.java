package org.example.springbootnotationsapi.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootnotationsapi.model.notes.Note;
import org.example.springbootnotationsapi.model.notes.dto.get.GetNoteResponse;
import org.example.springbootnotationsapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.springbootnotationsapi.model.notes.dto.create.CreateNoteResponse;
import org.example.springbootnotationsapi.model.notes.dto.create.CreateNoteRequest;
import org.example.springbootnotationsapi.model.notes.dto.update.UpdateNoteRequest;
import org.example.springbootnotationsapi.model.notes.dto.update.UpdateNoteResponse;
import org.example.springbootnotationsapi.model.notes.dto.delete.DeleteNoteResponse;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    private static final int MAX_TITLE_LENGTH = 255;

    private final NoteRepository repository;

    public CreateNoteResponse create(CreateNoteRequest request) {
        Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);

        if (validationError.isPresent()) {
            return CreateNoteResponse.failed(validationError.get());
        }

        Note createdNote = repository.save(Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());

        return CreateNoteResponse.success(createdNote.getId());
    }

    public UpdateNoteResponse update(UpdateNoteRequest request) {
        Optional<Note> optionalNote = repository.findById(request.getId());

        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.invalidNoteId);
        }

        Note note = optionalNote.get();

        Optional<UpdateNoteResponse.Error> validationError = validateUpdateFields(request);

        if (validationError.isPresent()) {
            return UpdateNoteResponse.failed(validationError.get());
        }

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        repository.save(note);

        return UpdateNoteResponse.success(note);
    }

    public DeleteNoteResponse delete(long id) {
        Optional<Note> optionalNote = repository.findById(id);

        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.invalidNoteId);
        }

        Note note = optionalNote.get();

        repository.delete(note);

        return DeleteNoteResponse.success();
    }

    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        if (Objects.isNull(request.getTitle()) || request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        return Optional.empty();
    }

    public GetNoteResponse getNotes() {
        List<Note> notes = noteRepository.findAll();
        return GetNoteResponse.success(notes);
    }

    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        if (Objects.nonNull(request.getTitle()) && request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }
        return Optional.empty();
    }
}