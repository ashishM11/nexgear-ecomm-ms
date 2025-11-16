package com.ecommerce.app.service;

import com.ecommerce.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrMobile) {

        // Check if input is email
        if (emailOrMobile.contains("@")) {
            return userRepository
                    .findByUserEmail(emailOrMobile)
                    .map( u -> (UserDetails) u)
                    .orElseThrow(() -> new UsernameNotFoundException("Given UserEmail not found"));
        }

        // Otherwise treat as mobile
        return userRepository
                .findByUserMobile(emailOrMobile)
                .map( u -> (UserDetails) u)
                .orElseThrow(() -> new UsernameNotFoundException("Given UserMobile not found"));
    }

}
