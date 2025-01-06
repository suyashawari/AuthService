package org.auth.service;

import org.auth.entities.UserInfo;
import org.auth.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {
    private String username;
    private String password;

    public CustomUserDetails(UserInfo byUsername) {
        this.username = byUsername.getUserName();
        this.password = byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();
        for (UserRole userRole : byUsername.getRoles()) {
            auths.add(new SimpleGrantedAuthority(userRole.getRole().toUpperCase()));
        }
        this.authorities = auths;
    }

    Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
