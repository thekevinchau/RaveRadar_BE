package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.ProfilePersonalDetails;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalDetailsDTO {
    private String birthday;
    private String phoneNumber;

    public PersonalDetailsDTO(ProfilePersonalDetails details){
        this.birthday = String.valueOf(details.getBirthday());
        this.phoneNumber = details.getPhoneNumber();
    }
}
