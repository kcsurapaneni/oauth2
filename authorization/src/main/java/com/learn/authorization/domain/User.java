package com.learn.authorization.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * @author Krishna Chaitanya
 */
@JsonDeserialize // https://stackoverflow.com/a/75874873/7078743
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

    private Long id;

    private String name;

    private String username;

    private String password;

    private String address;

    private Boolean isActive;

    private LocalDateTime createdAt;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles.stream().filter(Role.class::isInstance).map(e -> (Role) e).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
