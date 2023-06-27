package com.example.daybook.repository;

import com.example.daybook.models.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
    Iterable<Notes> findByTitle(String title) throws Exception;

}