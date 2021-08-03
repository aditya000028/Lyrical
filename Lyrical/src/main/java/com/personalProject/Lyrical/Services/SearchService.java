package com.personalProject.Lyrical.Services;

import com.personalProject.Lyrical.Models.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

//    @Value("${geniusURL}")
//    public String geniusURL;

    public List<Song> makeAPICall(String songName) {
        return List.of(
                new Song(
                        1L,
                        songName,
                        "Drake",
                        "Scorpion",
                        LocalDate.now(),
                        "Rap",
                        163,
                        "OVO"
                )
        );
    }

    // make request to api to get/store results of a song request

    // have a method to set the url where you want to request to

    // have a method to actually make the request
}