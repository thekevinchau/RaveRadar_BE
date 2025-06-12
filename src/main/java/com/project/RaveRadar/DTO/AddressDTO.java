package com.project.RaveRadar.DTO;


import com.project.RaveRadar.models.Address;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AddressDTO {

    private UUID id;
    private String venueName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.venueName = address.getVenueName();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.zip = address.getZip();
        this.country = address.getCountry();
        this.createdAt = address.getCreatedAt();
        this.updatedAt = address.getUpdatedAt();
    }
}
