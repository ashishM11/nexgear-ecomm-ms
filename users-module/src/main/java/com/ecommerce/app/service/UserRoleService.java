package com.ecommerce.app.service;

import com.ecommerce.app.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;



}
