package com.learn.authorization.config;

import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    UserDetailsService userDetailsService() {
        var kc = User.withDefaultPasswordEncoder().username("kc").password("password").build();
        var ganesh = User.withDefaultPasswordEncoder().username("ganesh").password("password").build();
        return new InMemoryUserDetailsManager(kc, ganesh);
    }

}
