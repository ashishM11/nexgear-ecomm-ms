package com.ecommerce.app.events;

import com.ecommerce.app.constants.UserRoleEnum;
import com.ecommerce.app.constants.UserRolePrivilegeEnum;
import com.ecommerce.app.model.Password;
import com.ecommerce.app.model.User;
import com.ecommerce.app.model.UserRole;
import com.ecommerce.app.model.UserRolePrivilege;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.repository.UserRolePrivilegeRepository;
import com.ecommerce.app.repository.UserRoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final UserRoleRepository roleRepository;

    private final UserRolePrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserRepository userRepository, UserRoleRepository roleRepository, UserRolePrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    UserRolePrivilege createPrivilegeIfNotFound(String name) {

        Optional<UserRolePrivilege> privilegeOptional = privilegeRepository.findByUserRolePrivilegeName(name);

        if(privilegeOptional.isEmpty()){
            UserRolePrivilege role = UserRolePrivilege.builder()
                    .userRolePrivilegeName(name)
                    .build();
            return privilegeRepository.save(role);
        }

        return privilegeOptional.get();
    }

    @Transactional
    private UserRole createRoleIfNotFound(String name, Collection<UserRolePrivilege> privileges) {

        Optional<UserRole> roleOptional = roleRepository.findByUserRoleName(name);

        if(roleOptional.isEmpty()){
            UserRole role = UserRole.builder()
                    .userRoleName(name)
                    .userRolePrivileges(privileges)
                    .build();
            return roleRepository.save(role);
        }
        return roleOptional.get();
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        alreadySetup = validateAdminPresent().isPresent();
        if (!alreadySetup) {
            UserRolePrivilege readPrivilege
                    = createPrivilegeIfNotFound(UserRolePrivilegeEnum.READ.name());
            UserRolePrivilege writePrivilege
                    = createPrivilegeIfNotFound(UserRolePrivilegeEnum.WRITE.name());
            UserRolePrivilege deletePrivilege
                    = createPrivilegeIfNotFound(UserRolePrivilegeEnum.DELETE.name());

            final UserRole adminRole = createRoleIfNotFound(UserRoleEnum.ROLE_ADMIN.name(), List.of(readPrivilege, writePrivilege, deletePrivilege));
            createRoleIfNotFound(UserRoleEnum.ROLE_USER.name(), List.of(readPrivilege, writePrivilege));

            User user = new User();
            user.setUserFName("Test");
            user.setUserLName("Test");
            user.setUserPassword(Password.builder().encryptedPassword(passwordEncoder.encode("test")).passwordCreationDT(LocalDateTime.now()).build());
            user.setUserEmail("test@test.com");
            user.setUserGender("A");
            user.setUserDOB(LocalDate.of(2025, 1, 1));
            user.setUserMobile("9167072373");
            user.setUserRoles(Collections.singletonList(adminRole));
            user.setUserAccountEnabled(true);
            user.setUserAccountNonExpired(true);
            user.setUserAccountNonLocked(true);
            user.setUserCredentialsNonExpired(true);
            user.setUserCreationDT(LocalDateTime.now());
            userRepository.save(user);
        }
    }

    private Optional<User> validateAdminPresent() {
        return userRepository.findByUserEmail("test@test.com");
    }
}
