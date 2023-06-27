package com.example.daybook.contollers;

import com.example.daybook.models.Notes;
import com.example.daybook.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NotesController {
    @Autowired
    private NotesRepository notesRepository;
    @GetMapping("/notes")
    public String notesHome (Model model) {
        Iterable<Notes> notes = notesRepository.findAll();
        model.addAttribute("notes", notes);
        return "notes";
    }

    @GetMapping("/notes/add")
    public String notesAdd (Model model) {
        return "notes-add";
    }
    @PostMapping("/notes/add")
    public String homeNotesAdd (@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Notes notes =new Notes(title, anons, full_text);
        notesRepository.save(notes);
        return "redirect:/notes";
    }
    @GetMapping("/notes/{id}")
    public String notesDetails (@PathVariable(value = "id") long id, Model model) {
        if(!notesRepository.existsById(id)) {
            return "redirect:/notes";
        }
        Optional<Notes> notes = notesRepository.findById(id);
        ArrayList<Notes> res = new ArrayList<>();
        notes.ifPresent(res::add);
        model.addAttribute("notes", res);
        return "notes-details";
    }
    @GetMapping("/notes/{id}/edit")
    public String notesEdit (@PathVariable(value = "id") long id, Model model) {
        if(!notesRepository.existsById(id)) {
            return "redirect:/notes";
        }
        Optional<Notes> notes = notesRepository.findById(id);
        ArrayList<Notes> res = new ArrayList<>();
        notes.ifPresent(res::add);
        model.addAttribute("notes", res);
        return "notes-edit";
    }
    @PostMapping("/notes/{id}/edit")
    public String notesUpdate (@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Notes notes = notesRepository.findById(id).orElseThrow();
        notes.setTitle(title);
        notes.setAnons(anons);
        notes.setFull_text(full_text);
        notesRepository.save(notes);
        return "redirect:/notes";
    }
    @PostMapping("/notes/{id}/remove")
    public String notesRemove (@PathVariable(value = "id") long id, Model model) {
        Notes notes = notesRepository.findById(id).orElseThrow();
        notesRepository.delete(notes);
        return "redirect:/notes";
    }




    @GetMapping("/notes/search")
    public String searchPage(){
        return "search";
    }

    @PostMapping("/notes/search")
    public String searchPage(@RequestParam("searchString") String searchString, Model model){
        if(searchString != null){
            try {
                Iterable<Notes> searchResult = notesRepository.findByTitle(searchString);
                model.addAttribute("searchResult", searchResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "search";
    }
}

