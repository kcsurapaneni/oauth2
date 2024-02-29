package com.learn.client.controller;

import org.springframework.web.bind.annotation.*;

import java.security.*;

/**
 * @author Krishna Chaitanya
 */
@RestController
@RequestMapping("/")
public class AppController {

    @GetMapping
    public String welcome(Principal principal) {
        return "Welcome " + principal.getName().toUpperCase() + "!, to the CLIENT application";
    }

}
