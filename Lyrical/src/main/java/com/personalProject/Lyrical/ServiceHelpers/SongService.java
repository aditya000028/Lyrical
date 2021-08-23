package com.personalProject.Lyrical.ServiceHelpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.personalProject.Lyrical.Models.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Value("${AWS_SONG_INFO_API}")
    private String AWS_SONG_INFO_API;

    public long getIDFromObj(JsonObject obj) {
        return obj.get("key").getAsLong();
    }

    public String getNameFromObj(JsonObject obj) {
        return obj.get("title").getAsString();
    }

    public String getArtistFromObj(JsonObject obj) {
        return obj.get("subtitle").getAsString();
    }

    public String getImageURLFromObj(JsonObject obj) {
        return obj.get("images").getAsJsonObject().get("coverart").getAsString();
    }

    public String getTextFromObj(JsonObject obj) {
        return obj.get("text").getAsString();
    }

    public List<String> getLyricsFromObj(JsonObject obj) {

        JsonArray lyricsArray = obj.get("text").getAsJsonArray();
        List<String> lyrics = new ArrayList<>();

        for (JsonElement lyric : lyricsArray) {
            lyrics.add(lyric.getAsString());
        }

        return lyrics;
    }

    public Song getSongInformation(long id) throws IOException {
        return parseResponse(makeAPICall(id));
    }

    private JsonObject makeAPICall(long id) throws IOException {
        String request = AWS_SONG_INFO_API + id;

        URL url = new URL(request);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(httpURLConnection.getInputStream()));
        httpURLConnection.disconnect();

        return jsonElement.getAsJsonObject();
    }

    private Song parseResponse(JsonObject result) {
        Song song = new Song();
        setSongInfo(song, result);

        return song;
    }

    private void setSongInfo(Song song, JsonObject jsonObject) {
        song.setId(getIDFromObj(jsonObject));
        song.setArtist(getArtistFromObj(jsonObject));
        song.setName(getNameFromObj(jsonObject));
        song.setImageURL(getImageURLFromObj(jsonObject));

        JsonObject songObj = jsonObject.get("sections").getAsJsonArray().get(0).getAsJsonObject();
        JsonArray songDataArray = songObj.get("metadata").getAsJsonArray();

        for (JsonElement element : songDataArray) {
            JsonObject dataObj = (JsonObject) element;
            switch (dataObj.get("title").getAsString()) {
                case "Album":
                    song.setAlbum(getTextFromObj(dataObj));
                    break;
                case "Label":
                    song.setLabels(getTextFromObj(dataObj));
                    break;
                case "Released":
                    song.setReleaseDate(getTextFromObj(dataObj));
                    break;
            }
        }

        JsonObject lyricsObj = jsonObject.get("sections").getAsJsonArray().get(1).getAsJsonObject();
        song.setLyrics(getLyricsFromObj(lyricsObj));
    }
}
