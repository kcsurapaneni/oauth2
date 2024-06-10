package com.kc.te.config;

import com.kc.te.service.*;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.authentication.*;
import org.springframework.security.oauth2.core.*;
import org.springframework.util.*;
import org.springframework.web.servlet.function.*;

import java.security.*;

import static org.springframework.cloud.gateway.server.mvc.common.MvcUtils.*;

/**
 * @author Krishna Chaitanya
 */
public class JwtScopesTokenFilterFunctions {

    private JwtScopesTokenFilterFunctions(){
        throw new UnsupportedOperationException("Utility class");
    }

    public static HandlerFilterFunction<ServerResponse, ServerResponse> token(JwtService jwtService) {
        return (request, next) -> {
            Principal principle = request.servletRequest().getUserPrincipal();
            if (principle instanceof OAuth2AuthenticationToken token) {
                String clientRegistrationId = token.getAuthorizedClientRegistrationId();
                OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                        .withClientRegistrationId(clientRegistrationId).principal(token).build();
                OAuth2AuthorizedClientManager clientManager = getApplicationContext(request)
                        .getBean(OAuth2AuthorizedClientManager.class);
                OAuth2AuthorizedClient authorizedClient = clientManager.authorize(authorizeRequest);
                if (ObjectUtils.isEmpty(authorizedClient)) {
                    return next.handle(request);
                }
                OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
                String tokenValue = jwtService.generate(accessToken.getTokenValue(), ServerRequest.from(request).build().servletRequest()).getTokenValue();
                ServerRequest modified = ServerRequest.from(request)
                        .headers(httpHeaders -> httpHeaders.setBearerAuth(tokenValue)).build();
                return next.handle(modified);
            }
            return next.handle(request);
        };
    }

}
