package com.ecommerce.app.controller;

import com.ecommerce.app.dto.UserRequestDTO;
import com.ecommerce.app.dto.UserSignInRequestDTO;
import com.ecommerce.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> userSignUpRequest(@RequestBody UserRequestDTO requestDTO){
        return ResponseEntity.ok(userService.createNewUser(requestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> userSignInRequest(@RequestBody UserSignInRequestDTO requestDTO){
        return ResponseEntity.ok(userService.authenticateUser(requestDTO));
    }

}
