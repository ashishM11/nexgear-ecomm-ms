package com.ecommerce.app.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationEvent {

    private Long userId;

    private String userEmail;

    private LocalDate userDOB;

    private boolean userAccountEnabled;

    private boolean userAccountNonExpired;

    private boolean userAccountNonLocked;

    private boolean userCredentialsNonExpired;

}
