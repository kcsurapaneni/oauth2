package com.learn.authorization.controller;

import org.springframework.security.oauth2.core.endpoint.*;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;
import java.util.stream.*;

/**
 * @author Krishna Chaitanya
 */
@Controller
public class ConsentController {
    private final OAuth2AuthorizationConsentService authorizationConsentService;

    public ConsentController(OAuth2AuthorizationConsentService authorizationConsentService) {
        this.authorizationConsentService = authorizationConsentService;
    }

    @GetMapping(value = "/oauth2/consent")
    public String consent(
            Principal principal,
            Model model,
            @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
            @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
            @RequestParam(OAuth2ParameterNames.STATE) String state
    ) {
        // Remove scopes that were already approved
        Set<String> scopesToApprove = new HashSet<>();
        Set<String> previouslyApprovedScopes = new HashSet<>();
        OAuth2AuthorizationConsent previousConsent = this.authorizationConsentService.findById(clientId, principal.getName());
        for (String scopeFromRequest : StringUtils.delimitedListToStringArray(scope, " ")) {
            if (previousConsent != null && previousConsent.getScopes().contains(scopeFromRequest)) {
                previouslyApprovedScopes.add(scopeFromRequest);
            } else {
                scopesToApprove.add(scopeFromRequest);
            }
        }

        model.addAttribute("state", state);
        model.addAttribute("clientId", clientId);
        model.addAttribute("scopes", withDescription(scopesToApprove));
        model.addAttribute("previouslyApprovedScopes", withDescription(previouslyApprovedScopes));
        model.addAttribute("principalName", principal.getName());

        return "oauth2/consent";
    }

    private Set<ScopeWithDescription> withDescription(Set<String> scopes) {
        return scopes
                .stream()
                .map(ScopeWithDescription::new)
                .collect(Collectors.toSet());
    }

    private static class ScopeWithDescription {
        public final String scope;
        public final String description;

        private static final String DEFAULT_DESCRIPTION = "UNKNOWN SCOPE - We cannot provide information about this permission, use caution when granting this.";
        private static final Map<String, String> scopeDescriptions = new HashMap<>();

        static {
            scopeDescriptions.put(
                    "openid",
                    "use openid to verify your identity"
            );
            scopeDescriptions.put(
                    "profile",
                    "profile information for personalization"
            );
        }

        ScopeWithDescription(String scope) {
            this.scope = scope;
            this.description = scopeDescriptions.getOrDefault(scope, DEFAULT_DESCRIPTION);
        }
    }
}
