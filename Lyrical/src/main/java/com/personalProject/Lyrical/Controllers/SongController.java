package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.Models.Song;
import com.personalProject.Lyrical.ServiceHelpers.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class SongController {

    final private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/song")
    public String getSong(@RequestParam("id") long id, Model model) {

        try {
            Song song = songService.getSongInformation(id);
            model.addAttribute("songInfo", song);
        } catch (IOException ioException) {
            model.addAttribute("error", ioException.getMessage());
        }

        return "song";
    }
}