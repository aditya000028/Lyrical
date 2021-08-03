package com.personalProject.Lyrical.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SongController {

    @GetMapping("/song")
    public String getSong(@RequestParam(name = "name", required = false, defaultValue = "Nonstop") String name, Model model) {
        model.addAttribute("name", name);
        return "song";
    }
}