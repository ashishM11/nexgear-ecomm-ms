package com.ecommerce.app.service;

import com.ecommerce.app.constants.UserRoleEnum;
import com.ecommerce.app.dto.UserRequestDTO;
import com.ecommerce.app.dto.UserSignInRequestDTO;
import com.ecommerce.app.mapper.UserMapper;
import com.ecommerce.app.model.*;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public void updateProfile() {

    }


    public void changePassword() {

    }


    public void deactivateUserAccount() {

    }


    public void reactivateUserAccount() {

    }


    public void lockUserAccount() {

    }


    public void unlockUserAccount() {

    }


    public void deleteUserAccount() {

    }

    @Transactional
    public boolean createNewUser(UserRequestDTO requestDTO){

        if(requestDTO.getRawPassword().trim().equals(requestDTO.getRetypePassword().trim())){
            UserRole userRole = getUserRole();
            if(Objects.nonNull(userRole)){
                LocalDateTime currentTs = LocalDateTime.now();
                User user= UserMapper.INSTANCE.fromUserRequestDTOToUserModel(requestDTO);
                user.setUserRoles(Collections.singleton(userRole));
                user.setUserAccountEnabled(true);
                user.setUserAccountNonLocked(true);
                user.setUserAccountNonExpired(true);
                user.setUserCredentialsNonExpired(true);
                user.setUserCreationDT(currentTs);
                user.setUserPassword(getPassword(requestDTO.getRawPassword(),currentTs));
                User userOp =  userRepository.save(user);
                return Objects.nonNull(userOp.getUserId());
            }
           return false;
        }else{
            return false;
        }
    }

    private Password getPassword(String rawPassword, LocalDateTime created_ts){
        return Password.builder()
                .encryptedPassword(passwordEncoder.encode(rawPassword.trim()))
                .passwordCreationDT(created_ts)
                .build();
    }

    private UserRole getUserRole(){
        final Optional<UserRole> byUserRoleName = userRoleRepository.findByUserRoleName(UserRoleEnum.ROLE_USER.name());
        return byUserRoleName.orElse(null);
    }

    public String authenticateUser(UserSignInRequestDTO requestDTO) {
        final String userName = requestDTO.getUserEmailOrMobile();
        final String password = requestDTO.getUserPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        if (authentication.isAuthenticated()) {
            ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
            return jwtService.generateJwtToken(user);
        }
        return "Username Or Password is Incorrect";
    }
}
