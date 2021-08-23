package com.personalProject.Lyrical.ServiceHelpers;

import com.personalProject.Lyrical.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Value("${AWS_SEARCH_API}")
    private String AWS_SEARCH_API;

    private final SongService songService;

    @Autowired
    SearchService(SongService songService) {
        this.songService = songService;
    }

    public List<Song> getQueryResult(String songName) throws IOException, URISyntaxException {
        return parseResponse(makeAPICall(songName));
    }

    private JsonObject makeAPICall(String songName) throws IOException, URISyntaxException {
        String request = AWS_SEARCH_API + new URI(null, null, null, songName, null);

        URL url = new URL(request);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(httpURLConnection.getInputStream()));
        httpURLConnection.disconnect();

        return jsonElement.getAsJsonObject();
    }

    private List<Song> parseResponse(JsonObject result) {
        List<Song> results = new ArrayList<>();
        JsonArray matches = result.get("response").getAsJsonObject().get("hits").getAsJsonArray();

        for (JsonElement matchElement : matches) {
            JsonObject matchObject = ((JsonObject) matchElement).get("result").getAsJsonObject();

            Song song = new Song();
            song.setId(songService.getIDFromObj(matchObject));
            song.setName(songService.getNameFromObj(matchObject));
            song.setArtist(songService.getArtistFromObj(matchObject));
            song.setImageURL(songService.getImageURLFromObj(matchObject));

            results.add(song);
        }

        return results;
    }
}