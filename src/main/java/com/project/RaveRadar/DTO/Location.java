package com.project.RaveRadar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String venueName;
    private String address;
    private String city;
    private String state;
    private String zipcode;
}
