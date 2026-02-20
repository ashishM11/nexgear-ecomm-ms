package com.ecommerce.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblCustAddress")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString(exclude = {"customer"})
@EqualsAndHashCode(exclude = {"customer"})
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId", nullable = false)
    private Customer customer;

    @Column(nullable = false, length = 150)
    private String street;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 10)
    private String postalCode;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'HOME'")
    private String addressType;

    @Column(nullable = false)
    private boolean isDefault;
}
