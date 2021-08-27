package com.personalProject.Lyrical.ServiceHelpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.personalProject.Lyrical.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ProfileService {

    @Value("${AWS_PROFILE_API}")
    private String AWS_PROFILE_API;

    public User getUserProfile(String username) throws IOException {
        return parseResponse(makeAPICall(username));
    }

    private JsonObject makeAPICall(String username) throws IOException {
        String request = AWS_PROFILE_API + username;

        URL url = new URL(request);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(httpURLConnection.getInputStream()));
        httpURLConnection.disconnect();

        return jsonElement.getAsJsonObject();
    }

    private User parseResponse(JsonObject obj) {
        User user = new User();

        user.setId(obj.get("id").getAsLong());
        user.setAddress(obj.get("address").getAsString());
        user.setName(obj.get("name").getAsString());
        user.setEmail(obj.get("email").getAsString());
        user.setBirthdate(obj.get("birthdate").getAsString());

        return user;
    }

}
