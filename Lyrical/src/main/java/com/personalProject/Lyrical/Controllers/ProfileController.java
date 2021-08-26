package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.Models.User;
import com.personalProject.Lyrical.ServiceHelpers.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    final private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model) {

        if (authentication != null) {

            // make a call from profile service helper to api to fetch user info from db


            return "profile";
        } else {
            // Return home with a message to login
            // Or direct straight to login
            return "home";
        }
    }
}
