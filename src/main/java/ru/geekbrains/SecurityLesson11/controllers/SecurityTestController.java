package ru.geekbrains.SecurityLesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.SecurityLesson11.entities.User;
import ru.geekbrains.SecurityLesson11.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class SecurityTestController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
