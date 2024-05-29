package com.kc.te.service;

import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.oauth2.jose.jws.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

import static com.kc.te.util.RequestUtil.*;

/**
 * @author Krishna Chaitanya
 */
@Service
public class JwtService {

    private final List<String> acceptedScopes = List.of("sub", "aud");
    private final List<String> appScopes = List.of("app_READ", "app_WRITE", "app_ADMIN");

    private final RSAKey rsaKey;
    private final JWKSource<SecurityContext> jwkSource;

    public JwtService(RSAKey rsaKey, JWKSource<SecurityContext> jwkSource) {
        this.rsaKey = rsaKey;
        this.jwkSource = jwkSource;
    }

    public Jwt generate(@AuthenticationPrincipal Jwt jwt, HttpServletRequest request) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plusSeconds(3600); // 1 hour
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder();
        claimsBuilder
                .id(UUID.randomUUID().toString())
                .notBefore(issuedAt)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .issuer(getContextPath(request))
                .claim("appScopes", appScopes);
        for (Map.Entry<String, Object> fetchRequiredClaim : fetchRequiredClaims(jwt)) {
            claimsBuilder.claim(fetchRequiredClaim.getKey(), fetchRequiredClaim.getValue());
        }
        JwsHeader.Builder jwsHeaderBuilder = JwsHeader.with(SignatureAlgorithm.RS256).keyId(rsaKey.getKeyID());
        return new NimbusJwtEncoder(jwkSource).encode(JwtEncoderParameters.from(jwsHeaderBuilder.build(), claimsBuilder.build()));
    }

    private List<Map.Entry<String, Object>> fetchRequiredClaims(Jwt jwt) {
        List<Map.Entry<String, Object>> copiedClaims = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jwt.getClaims().entrySet()) {
            if (acceptedScopes.contains(entry.getKey())) {
                copiedClaims.add(entry);
            }
        }
        return copiedClaims;
    }

    public String getJwks() {
        return new JWKSet(rsaKey).toString();
    }

}
