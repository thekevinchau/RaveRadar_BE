package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.ProfilePersonalDetails;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetailsDTO {
    private String birthday;
    private String phoneNumber;

    public PersonalDetailsDTO(ProfilePersonalDetails details){
        this.birthday = String.valueOf(details.getBirthday());
        this.phoneNumber = details.getPhoneNumber();
    }
}
