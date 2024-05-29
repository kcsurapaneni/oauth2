package com.kc.te.config;

import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.*;
import org.springframework.context.annotation.*;


/**
 * @author Krishna Chaitanya
 */
@Configuration
public class KeysConfiguration {

    @Bean
    public RSAKey rsaKey(JwtKeyProperties jwtKeyProperties) {
        return new RSAKey.Builder(jwtKeyProperties.rsaPublic())
                .privateKey(jwtKeyProperties.rsaPrivate())
                .keyID(jwtKeyProperties.id())
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        return (j, sc) -> j.select(new JWKSet(rsaKey));
    }

}
