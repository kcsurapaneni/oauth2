package com.learn.client.controller;

import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.authentication.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

/**
 * @author Krishna Chaitanya
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public HelloController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping
    String hello(OAuth2AuthenticationToken auth2AuthenticationToken) {
        var oAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(auth2AuthenticationToken.getAuthorizedClientRegistrationId(), auth2AuthenticationToken.getName());
        RestClient restClient = RestClient.builder().build();
        return restClient.get()
                .uri("http://127.0.0.1:7070/hello")
                .header("Authorization", "Bearer " + oAuth2AuthorizedClient.getAccessToken().getTokenValue())
                .retrieve().body(String.class);
    }

}
