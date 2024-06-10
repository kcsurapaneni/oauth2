package com.kc.te.config;

import org.springframework.web.servlet.function.*;

/**
 * @author Krishna Chaitanya
 */
public class RequestPredicates {

    private RequestPredicates() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static RequestPredicate apiCheck() {
        return request -> !request.uri().getPath().endsWith("/jwks");
    }

}
