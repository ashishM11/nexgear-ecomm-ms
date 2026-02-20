package com.ecommerce.app.dto;
import com.ecommerce.app.constants.AddressTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerAddressRequestDTO {

    @NotBlank(message = "Street is required")
    @Size(max = 150, message = "Street can be max 150 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City can be max 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State can be max 100 characters")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country can be max 100 characters")
    private String country;

    @NotBlank(message = "Postal code is required")
    @Size(max = 6, message = "Postal code can be max 6 characters")
    @Pattern(regexp = "^[0-9A-Za-z-]+$", message = "Invalid postal code")
    private String postalCode;

    @NotNull(message = "Address type is required")
    private AddressTypeEnum addressType;

    private Boolean isDefault;

}
