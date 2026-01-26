package com.ecommerce.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userPassword", "userRoles"})
@EqualsAndHashCode(exclude = {"userPassword", "userRoles"})
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "userFName", nullable = false, length = 25)
    private String userFName;

    @Column(name = "userLName", nullable = false, length = 25)
    private String userLName;

    @Column(name = "userMobile", nullable = false, length = 10, unique = true)
    private String userMobile;

    @Column(name = "userEmail", nullable = false, length = 45, unique = true)
    private String userEmail;

    @Column(name = "userGender", nullable = false)
    private String userGender;

    @Column(name = "userDOB")
    private LocalDate userDOB;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userPasswordId", nullable = false, referencedColumnName = "passwordId")
    private Password userPassword;

    @Column(name = "userAccountNonExpired")
    private boolean userAccountNonExpired;

    @Column(name = "userAccountNonLocked")
    private boolean userAccountNonLocked;

    @Column(name = "userCredentialsNonExpired")
    private boolean userCredentialsNonExpired;

    @Column(name = "userAccountEnabled")
    private boolean userAccountEnabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "tblUsersWithRoles",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "userRoleId", referencedColumnName = "userRoleId")
    )
    private Collection<UserRole> userRoles;

    @Column(name = "userCreationDT", nullable = false)
    private LocalDateTime userCreationDT;
}
