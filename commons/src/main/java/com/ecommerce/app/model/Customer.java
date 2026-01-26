package com.ecommerce.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tblCustomer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"customerAddress"})
//@EqualsAndHashCode(exclude = {"customerAddress"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column(name = "userId", nullable = false, unique = true)
    private Long userId;

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CustomerAddress> customerAddress;

    @Column(name = "custCreationDT")
    private LocalDateTime custCreationDT;

    @Column(name = "custLastModificationDT")
    private LocalDateTime custLastModificationDT;
}
