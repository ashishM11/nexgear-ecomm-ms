package com.ecommerce.app.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

public class ApplicationUser implements UserDetails {

    private  final User user;

    public ApplicationUser(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(user.getUserRoles())) {
            return List.of();
        }
        return user.getUserRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getUserRoleName()))
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
