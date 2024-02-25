package com.learn.authorization.config;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import java.security.interfaces.*;


/**
 * @author Krishna Chaitanya
 */
@Configuration
public class KeysConfiguration {

    @Bean
    public JWKSource<SecurityContext> jwkSource(
            @Value("${jwt.key.id}") String keyId,
            @Value("${jwt.key.private}") RSAPrivateKey privateKey,
            @Value("${jwt.key.public}") RSAPublicKey publicKey) {

        var rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(keyId)
                .build();

        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }

}
