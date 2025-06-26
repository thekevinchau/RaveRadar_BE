package com.project.RaveRadar.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
public class AddressPayload {

    @NotBlank(message = "Venue name is required")
    @Size(max = 100, message = "Venue name must be less than 100 characters")
    private String venueName;

    @NotBlank(message = "Street is required")
    @Size(max = 150, message = "Street must be less than 150 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City must be less than 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State must be less than 100 characters")
    private String state;

    @Positive(message = "Zip code must be a positive number")
    @Digits(integer = 10, fraction = 0, message = "Zip code must be a number with up to 10 digits")
    private int zipCode;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country must be less than 100 characters")
    private String country;

    @PastOrPresent(message = "Created at must be in the past or present")
    private ZonedDateTime createdAt;

    @PastOrPresent(message = "Updated at must be in the past or present")
    private ZonedDateTime updatedAt;
}
