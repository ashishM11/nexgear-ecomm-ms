package com.ecommerce.app.controller;

import com.ecommerce.app.dto.UserRequestDTO;
import com.ecommerce.app.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("Welcome to NexGear - UserModule");
    }

//    @GetMapping("/find")
//    public ResponseEntity<?> getUserByEmailOrMobileOrId(
//            @RequestParam(name = "userId") Long userId,
//            @RequestParam(name = "userEmail") String userEmail,
//            @RequestParam(name = "userMobile") String userMobile
//    ){
//        return (userId == null && userEmail == null && userMobile == null)
//                ? new ResponseEntity<>("No Input provided to find UserCreationEvent", HttpStatus.BAD_REQUEST)
//                : ResponseEntity.ok(userService.findUserBy(userId,userEmail,userMobile));
//    }

}
