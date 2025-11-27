package com.ecommerce.app.controller;

import com.ecommerce.app.dto.UserRequestDTO;
import com.ecommerce.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("Welcome to NexGear - UserModule");
    }

}
