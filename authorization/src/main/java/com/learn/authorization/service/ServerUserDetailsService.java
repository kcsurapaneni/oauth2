package com.learn.authorization.service;

import com.learn.authorization.repository.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

/**
 * @author Krishna Chaitanya
 */
@Component
public class ServerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ServerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(username);
    }

}
