package com.kc.te.config;

import org.springframework.boot.context.properties.*;
import org.springframework.validation.annotation.*;

import java.security.interfaces.*;

/**
 * @author Krishna Chaitanya
 */
@Validated
@ConfigurationProperties(prefix = "jwt.key")
public record JwtKeyProperties(String id, RSAPrivateKey rsaPrivate, RSAPublicKey rsaPublic) {
}
