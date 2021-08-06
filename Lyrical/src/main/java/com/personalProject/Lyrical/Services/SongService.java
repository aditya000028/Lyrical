package com.personalProject.Lyrical.Services;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    public long getIDFromObj(JsonObject obj) {
        return obj.get("id").getAsLong();
    }

    public String getNameFromObj(JsonObject obj) {
        return obj.get("full_title").getAsString();
    }

    public String getArtistFromObj(JsonObject obj) {
        return obj.get("primary_artist").getAsJsonObject().get("name").getAsString();
    }

    public String getImageURLFromObj(JsonObject obj) {
        if (!obj.get("song_art_image_url").getAsString().isEmpty()) {
            return obj.get("song_art_image_url").getAsString();
        } else {
            return obj.get("header_image_url").getAsString();
        }
    }
}
