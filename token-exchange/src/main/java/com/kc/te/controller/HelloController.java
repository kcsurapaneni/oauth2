package com.kc.te.controller;

import com.kc.te.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Krishna Chaitanya
 */
@RestController
@RequestMapping("/")
public class HelloController {

    private final JwtService jwtService;

    public HelloController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping(value = "/jwks", produces = MediaType.APPLICATION_JSON_VALUE)
    public String jwks() {
        return jwtService.getJwks();
    }

}
