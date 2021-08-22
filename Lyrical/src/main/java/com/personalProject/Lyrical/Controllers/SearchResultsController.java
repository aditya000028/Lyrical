package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.ServiceHelpers.SearchService;
import com.personalProject.Lyrical.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class SearchResultsController {

    private final SearchService searchService;

    @Autowired
    public SearchResultsController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/searchResults")
    public String searchSubmit(@RequestParam String songName, Model model) {

        try {
            List<Song> results = searchService.getQueryResult(songName);
            model.addAttribute("songList", results);
            model.addAttribute("query", songName);
        } catch (IOException | URISyntaxException exception) {
            model.addAttribute("error", exception.getMessage());
        }

        return "searchResults";

    }
}
