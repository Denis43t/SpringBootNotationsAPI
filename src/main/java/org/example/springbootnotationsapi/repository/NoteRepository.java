package org.example.springbootnotationsapi.repository;

import org.example.springbootnotationsapi.model.notes.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
}
