package com.ecommerce.app.response;

import java.io.Serializable;

public record CustomerAddressResponseRecord(
        Long customerAddressId,
        Long customerId,
        String street,
        String city,
        String state,
        String country,
        String postalCode,
        String addressType,
        boolean isDefault
) implements Serializable {
}
