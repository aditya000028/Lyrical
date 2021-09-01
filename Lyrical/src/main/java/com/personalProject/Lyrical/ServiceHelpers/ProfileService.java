package com.personalProject.Lyrical.ServiceHelpers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.personalProject.Lyrical.Models.User.Password;
import com.personalProject.Lyrical.Models.User.User;
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

    @Value("${AWS_PROFILE_CHANGE_PASSWORD_API}")
    private String AWS_PROFILE_CHANGE_PASSWORD_API;

    private final Gson gson = new Gson();

    public void loadUserProfile(User user, String idToken, String accessToken) throws IOException {
        String request = AWS_PROFILE_API + user.getUsername();
        setUserAttributesFromJson(makeAPICall(idToken, accessToken, "", "GET", request), user);
    }

    public String updateUserProfile(User user, String idToken, String accessToken) {

        String request = AWS_PROFILE_API + user.getUsername();

        try {
            makeAPICall(idToken, accessToken, gson.toJson(user), "PUT", request);
            return "Updated";
        } catch (IOException ioException) {
            return "Error: This email already has an account";
        }
    }

    public String changePassword(String idToken, String accessToken, String username, Password password) {

        String request = AWS_PROFILE_CHANGE_PASSWORD_API;
        request = request.replace("{username}", username);

        try {
            JsonObject result = makeAPICall(idToken, accessToken, gson.toJson(password), "PUT", request);
            return "Success: Password changed";
        } catch (IOException ioException) {
            return "Error: Unable to change password";
        }
    }

    private JsonObject makeAPICall(String idToken, String accessToken, String requestBody, String requestType, String requestURL) throws IOException {

        URL url = new URL(requestURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", idToken);
        httpURLConnection.setRequestProperty("Access-Token", accessToken);

        if (requestType.equals("PUT")) {
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            setRequestBody(httpURLConnection, requestBody);
        }

        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(httpURLConnection.getInputStream()));
        httpURLConnection.disconnect();

        return jsonElement.getAsJsonObject();
    }

    private void setUserAttributesFromJson(JsonObject obj, User user) {
        for (JsonElement element : obj.get("UserAttributes").getAsJsonArray()) {
            JsonObject attributeObject = (JsonObject) element;
            if (attributeObject.get("Name").getAsString().equals("name")) {
                user.setName(attributeObject.get("Value").getAsString());
            }
            if (attributeObject.get("Name").getAsString().equals("email")) {
                user.setEmail(attributeObject.get("Value").getAsString());
            }
        }
    }

    private void setRequestBody(HttpURLConnection httpURLConnection, String objString) throws IOException {
        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            byte[] input = objString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, objString.length());
        }
    }

}
