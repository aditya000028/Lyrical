package com.personalProject.Lyrical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class LyricalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyricalApplication.class, args);
	}

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
}
