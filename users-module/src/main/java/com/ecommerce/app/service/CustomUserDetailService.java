package com.ecommerce.app.service;

import com.ecommerce.app.model.ApplicationUser;
import com.ecommerce.app.model.User;
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
        User user;
        if (emailOrMobile.contains("@")) {
            user = userRepository
                    .findByUserEmail(emailOrMobile)
                    .orElseThrow(() -> new UsernameNotFoundException("Given UserEmail not found"));
        } else {
            user = userRepository
                    .findByUserMobile(emailOrMobile)
                    .orElseThrow(() -> new UsernameNotFoundException("Given UserMobile not found"));
        }
        return new ApplicationUser(user);
    }

}
