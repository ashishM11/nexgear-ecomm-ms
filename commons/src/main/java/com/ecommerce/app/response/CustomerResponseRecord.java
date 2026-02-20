package com.ecommerce.app.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record CustomerResponseRecord(
        Long custId,
        UserResponseRecord userResponseRecord,
        List<CustomerAddressResponseRecord> customerAddresses,
        LocalDateTime customerCreationDT,
        LocalDateTime customerLastModificationDT
) implements Serializable {
}
