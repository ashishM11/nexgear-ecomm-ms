package com.ecommerce.app.service;

import com.ecommerce.app.model.UserRolePrivilege;
import com.ecommerce.app.repository.UserRolePrivilegeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRolePrivilegeService {

    private final UserRolePrivilegeRepository userRolePrivilegeRepository;

}
