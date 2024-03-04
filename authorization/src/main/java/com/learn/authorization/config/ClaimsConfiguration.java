package com.learn.authorization.config;

import com.learn.authorization.domain.*;
import com.learn.authorization.service.*;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.token.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class ClaimsConfiguration {

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtOAuth2TokenCustomizer(ServerUserDetailsService userDetailsService) {
        return context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                var userDetails = userDetailsService.loadUserByUsername(context.getPrincipal().getName());
                if (userDetails instanceof User user) {
                    context.getClaims().claims(claims ->
                            claims.put("userId", String.valueOf(user.getId()))
                    );
                }
            }
        };
    }

}
