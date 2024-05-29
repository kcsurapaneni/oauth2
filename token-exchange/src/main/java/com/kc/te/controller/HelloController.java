package com.kc.te.controller;

import com.kc.te.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.*;
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

    @GetMapping
    public ResponseEntity<Void> hello(@AuthenticationPrincipal Jwt jwt, HttpServletRequest request) throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Token", jwtService.generate(jwt, request).getTokenValue());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping(value = "/jwks", produces = MediaType.APPLICATION_JSON_VALUE)
    public String jwks() {
        return jwtService.getJwks();
    }

}
