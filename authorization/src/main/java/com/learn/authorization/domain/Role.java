package com.learn.authorization.domain;

import org.springframework.security.core.*;

/**
 * @author Krishna Chaitanya
 */
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    private Boolean isActive;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

}
