package com.learn.resource.controller;

import com.learn.resource.service.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Krishna Chaitanya
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public Map<String, String> hello(@AuthenticationPrincipal Jwt jwt) {
        return Map.of("message", "hello, " + jwt.getSubject());
    }

    @GetMapping("/service")
    public Map<String, String> hello() {
        return Map.of("message", "hello, " + helloService.getSubjectName());
    }

}
