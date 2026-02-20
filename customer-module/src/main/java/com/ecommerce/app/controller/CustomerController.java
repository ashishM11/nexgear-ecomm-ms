package com.ecommerce.app.controller;

import com.ecommerce.app.dto.CustomerAddressRequestDTO;
import com.ecommerce.app.response.CustomerResponseRecord;
import com.ecommerce.app.service.CustomerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{custId}")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<?> getCustomerDetailById(@PathVariable("custId") Long custId) {
        if((custId == null) || (custId == 0L)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return new ResponseEntity<>(customerService.getCustomerDetailById(custId),HttpStatus.OK);
        }
    }

    @PostMapping("/address")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<?> addAddress(@Valid @RequestBody CustomerAddressRequestDTO addressRequestDTO) {

    }



}
