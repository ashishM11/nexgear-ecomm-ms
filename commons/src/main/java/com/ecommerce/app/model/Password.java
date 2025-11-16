package com.ecommerce.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tblPassword")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordId;

    @Column(name = "password",nullable = false)
    private String encryptedPassword;

    @Column(name = "passwordCreationDT")
    private LocalDateTime passwordCreationDT;

    @Column(name = "passwordLastUpdateDT",nullable = true)
    private LocalDateTime passwordLastUpdateDT;

}
