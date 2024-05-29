package com.kc.app.controller;

import org.springframework.security.core.annotation.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Krishna Chaitanya
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String hello(@AuthenticationPrincipal Jwt jwt) {
        return "Hello, " + jwt.getSubject();
    }

}
