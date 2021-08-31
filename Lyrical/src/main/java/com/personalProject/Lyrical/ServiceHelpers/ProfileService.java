package com.personalProject.Lyrical.ServiceHelpers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.personalProject.Lyrical.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ProfileService {

    @Value("${AWS_PROFILE_API}")
    private String AWS_PROFILE_API;

    private final Gson gson = new Gson();

    public void loadUserProfile(User user, String idToken) throws IOException {
        setUserAttributesFromJson(makeAPICall(user, idToken, "GET"), user);
    }

    public String updateUserProfile(User user, String idToken) throws IOException {
        JsonObject obj = makeAPICall(user, idToken, "PUT");
        return obj.get("message").getAsString();
    }

    private JsonObject makeAPICall(User user, String idToken, String requestType) throws IOException {
        String request = AWS_PROFILE_API + user.getUsername();

        URL url = new URL(request);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", idToken);

        if (requestType.equals("PUT")) {
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            setRequestBody(httpURLConnection, user);
        }

        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(httpURLConnection.getInputStream()));
        httpURLConnection.disconnect();

        return jsonElement.getAsJsonObject();
    }

    private void setUserAttributesFromJson(JsonObject obj, User user) {
        user.setUsername(obj.get("username").getAsString());
        user.setName(obj.get("name").getAsString());
        user.setEmail(obj.get("email").getAsString());

    }

    private void setRequestBody(HttpURLConnection httpURLConnection, User user) throws IOException {
        String userObjString = gson.toJson(user);
        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            byte[] input = userObjString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, userObjString.length());
        }
    }

}
