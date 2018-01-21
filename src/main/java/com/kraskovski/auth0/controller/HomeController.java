package com.kraskovski.auth0.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/home")
    protected String home(final Map<String, Object> model, final Principal principal) {
        if (principal == null) {
            return "redirect:/logout";
        }
        model.put("userId", principal);
        return "home";
    }
}
