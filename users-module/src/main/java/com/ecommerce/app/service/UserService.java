package com.ecommerce.app.service;

import com.ecommerce.app.constants.UserRoleEnum;
import com.ecommerce.app.dto.UserRequestDTO;
import com.ecommerce.app.mapper.UserMapper;
import com.ecommerce.app.model.*;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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

}
