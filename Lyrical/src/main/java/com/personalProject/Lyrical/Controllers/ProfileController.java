package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.Models.User;
import com.personalProject.Lyrical.ServiceHelpers.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;

@Controller
public class ProfileController {

    final private ProfileService profileService;
    final private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    public ProfileController(ProfileService profileService, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.profileService = profileService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {

        if (authentication != null && authentication.isAuthenticated()) {

            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String username = oAuth2AuthenticationToken.getName();
            OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                    oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                    username);
            String accessToken = client.getAccessToken().getTokenValue();

            try {
               User user = profileService.getUserProfile(username, accessToken);
               model.addAttribute("user", user);
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

    @PostMapping("/profile/update")
    public String updateUserProfile() {
        // perform update
        return "profile";
    }
}
