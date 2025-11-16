package com.ecommerce.app.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

public class MyApplicationUser extends User implements UserDetails {

    private  final User user;

    public MyApplicationUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(user.getUserRoles())) {
            return List.of();
        }
        return user.getUserRoles()
                .stream()
                .map(role -> (GrantedAuthority) role)
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getUserPassword().getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isUserAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isUserAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isUserCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isUserAccountEnabled();
    }
}
