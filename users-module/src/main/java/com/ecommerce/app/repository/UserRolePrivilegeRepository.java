package com.ecommerce.app.repository;

import com.ecommerce.app.model.UserRolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolePrivilegeRepository extends JpaRepository<UserRolePrivilege,Long> {

    Optional<UserRolePrivilege> findByUserRolePrivilegeName(String userRolePrivilege);

}
