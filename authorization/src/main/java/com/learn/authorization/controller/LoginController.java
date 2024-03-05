package com.learn.authorization.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Krishna Chaitanya
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
