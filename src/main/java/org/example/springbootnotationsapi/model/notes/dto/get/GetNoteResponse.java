package org.example.springbootnotationsapi.model.notes.dto.get;


import lombok.Builder;
import lombok.Data;
import org.example.springbootnotationsapi.model.notes.Note;

import java.util.List;

@Builder
@Data
public class GetNoteResponse {
    private Error error;

    private List<Note> userNotes;

    public enum Error {
        ok
    }

    public static GetNoteResponse success(List<Note> userNotes) {
        return builder().error(Error.ok).userNotes(userNotes).build();
    }

    public static GetNoteResponse failed(Error error) {
        return builder().error(error).userNotes(null).build();
    }
}

