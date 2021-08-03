package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.Services.SearchService;
import com.personalProject.Lyrical.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<Song> results = searchService.makeAPICall(songName);
        model.addAttribute("songList", results);
        model.addAttribute("query", songName);
        return "searchResults";
    }
}
