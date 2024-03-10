package org.example.hw_13.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "redirect:/home/all";
    }
}
