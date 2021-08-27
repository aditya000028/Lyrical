package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.Models.User;
import com.personalProject.Lyrical.ServiceHelpers.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;

@Controller
public class ProfileController {

    final private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {

        if (authentication != null && authentication.isAuthenticated()) {

            String username = oAuth2User.getAttribute("username");

            try {
               User user = profileService.getUserProfile(username);
               model.addAttribute("userProfile", user);
            } catch (IOException ioException) {
                model.addAttribute("error", ioException.getMessage());
            }

            return "profile";
        } else {
            // Return home with a message to login
            // Or direct straight to login
            return "home";
        }
    }

    @PostMapping("/profileUpdate")
    public String updateUserProfile() {
        // perform update
        return "profile";
    }
}
