package com.example.daybook.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/board")
    public String board (Model model) {
        model.addAttribute("title", "Доска");
        return "board";
    }
}
