package com.example.daybook.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
    @GetMapping("/tasks")
    public String home (Model model) {
        model.addAttribute("title", "Задачи");
        return "tasks";
    }
}