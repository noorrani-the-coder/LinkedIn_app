package com.hexaware.career_crafter_rest.api.config;

import com.hexaware.career_crafter_rest.api.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserInfoUserDetails implements UserDetails {

	private final Users user;

    public UserInfoUserDetails(Users user) {
        this.user = user;
    }

    public int getUserId() {
        return user.getUserid();  // expose userId for security expressions
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
            new SimpleGrantedAuthority(user.getRole().toUpperCase()) 
        );
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}