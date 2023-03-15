package kz.iitu.itse1903.abimoldayeva.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, DRIVER, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
