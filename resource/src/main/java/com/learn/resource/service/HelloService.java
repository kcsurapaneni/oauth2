package com.learn.resource.service;

import org.springframework.security.access.prepost.*;
import org.springframework.security.core.context.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.*;

/**
 * @author Krishna Chaitanya
 */
@Service
public class HelloService {

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public String getSubjectName() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        }
        return "not-found";
    }

}
