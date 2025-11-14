package com.ecommerce.app.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblCustomer")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString(exclude = {"customerAddress"})
@EqualsAndHashCode(exclude = {"customerAddress"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerAddress> customerAddress;

    @Column(name = "custCreationDT", nullable = true)
    private LocalDateTime custCreationDT;

    @Column(name = "custLastModificationDT",nullable = true)
    private LocalDateTime custLastModificationDT;
}
