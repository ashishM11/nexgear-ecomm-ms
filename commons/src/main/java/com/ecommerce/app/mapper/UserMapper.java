package com.ecommerce.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.ecommerce.app.dto.UserRequestDTO;
import com.ecommerce.app.model.User;
import com.ecommerce.app.model.UserRole;
import com.ecommerce.app.model.UserRolePrivilege;
import com.ecommerce.app.response.UserResponseRecord;
import com.ecommerce.app.response.UserRolePrivilegeResponseRecord;
import com.ecommerce.app.response.UserRoleResponseRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "userAccountEnabled", ignore = true)
    @Mapping(target = "userAccountNonExpired", ignore = true)
    @Mapping(target = "userAccountNonLocked", ignore = true)
    @Mapping(target = "userCredentialsNonExpired", ignore = true)
    @Mapping(target = "userCreationDT", ignore = true)
    @Mapping(source = "rawPassword", target = "password.encryptedPassword")
    User fromUserRequestDTOToUserModel(UserRequestDTO requestDTO);

    @Mapping(source = "user.userRoles", target = "userRoles", qualifiedByName = "setUserRole")
    UserResponseRecord fromUserModelToResponseRecord(User user);

    @Named("setUserRole")
    @Mapping(source = "userRole.userRolePrivileges",target = "privileges")
    default UserRoleResponseRecord setUserRole(UserRole userRole) {
        Collection<UserRolePrivilegeResponseRecord> userRolePrivilegeResponseRecords = new ArrayList<>();
        for(UserRolePrivilege userRolePrivilege :userRole.getUserRolePrivileges()){
            userRolePrivilegeResponseRecords.add(setUserRolePrivilege(userRolePrivilege));
        }
        return new UserRoleResponseRecord(userRole.getUserRoleId(), userRole.getUserRoleName(),userRolePrivilegeResponseRecords);
    }

    default UserRolePrivilegeResponseRecord setUserRolePrivilege(UserRolePrivilege userRolePrivilege){
        return new UserRolePrivilegeResponseRecord(userRolePrivilege.getUserRolePrivilegeId(), userRolePrivilege.getUserRolePrivilegeName());
    }

    Set<UserResponseRecord> fromUserModelsToUserResponseRecords(Set<User> users);

}
