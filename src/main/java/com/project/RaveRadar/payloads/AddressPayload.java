package com.project.RaveRadar.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class AddressPayload {

    private String venueName;
    private String street;
    private String city;
    private String state;
    private int zipCode;
    private String country;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
