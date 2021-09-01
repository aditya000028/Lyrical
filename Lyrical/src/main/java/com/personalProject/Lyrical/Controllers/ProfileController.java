package com.personalProject.Lyrical.Controllers;

import com.personalProject.Lyrical.Models.User;
import com.personalProject.Lyrical.ServiceHelpers.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getUserProfile(Authentication authentication, Model model, @AuthenticationPrincipal OidcUser oidcUser) {

        if (authentication != null && authentication.isAuthenticated()) {

            String idToken = oidcUser.getIdToken().getTokenValue();
            String accessToken = getAccessToken(authentication);

            try {
                User user = new User();
                user.setUsername(authentication.getName());
                profileService.loadUserProfile(user, idToken, accessToken);
                model.addAttribute("user", user);
            } catch (IOException ioException) {
                model.addAttribute("error", ioException.getMessage());
            }

            return "profile";
        } else {
            model.addAttribute("error", "You must be logged in to access your profile");
            return "home";
        }
    }

    @PostMapping("/profile")
    public String updateUserProfile(@ModelAttribute User user, Model model, Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {

        if (authentication != null && authentication.isAuthenticated()) {

            String idToken = oidcUser.getIdToken().getTokenValue();
            String accessToken = getAccessToken(authentication);

            String message = profileService.updateUserProfile(user, idToken, accessToken);
            if (!message.isEmpty()) {
                model.addAttribute("error", message);
            } else {
                model.addAttribute("message", message);
            }

            return "profile";

        } else {
            model.addAttribute("error", "You must be logged in to access your profile");
            return "home";
        }
    }

    private String getAccessToken(Authentication authentication) {

        if (authentication != null) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                    oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                    oAuth2AuthenticationToken.getName());
            return client.getAccessToken().getTokenValue();
        } else {
            return "";
        }
    }

}
