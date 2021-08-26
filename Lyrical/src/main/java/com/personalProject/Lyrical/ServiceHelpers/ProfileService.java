package com.personalProject.Lyrical.ServiceHelpers;

import com.google.gson.JsonObject;
import com.personalProject.Lyrical.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Value("${AWS_PROFILE_API}")
    private String AWS_PROFILE_API;

//    public User getUserProfile(String email) {
//        return parseResponse(makeAPICall(email));
//    }
//
//    private JsonObject makeAPICall(String email) {
//
//    }
//
//    private User parseResponse(JsonObject obj) {
//
//    }

}
