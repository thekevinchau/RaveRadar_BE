package com.project.RaveRadar.payloads;

import com.project.RaveRadar.enums.EdmGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
public class EventCreationPayload {


    @NotNull @NotBlank @NotEmpty
    private String eventName;
    @NotNull @NotBlank @NotEmpty
    private String description;
    @NotNull @NotBlank @NotEmpty
    private String eventType;
    @NotNull @NotBlank @NotEmpty
    private EdmGenre genre;
    @NotNull @NotBlank @NotEmpty
    private String externalLink;
    @NotNull @NotBlank @NotEmpty
    private String ageRestriction;
    @NotNull @NotBlank @NotEmpty
    private ZonedDateTime startDate;
    @NotNull @NotBlank @NotEmpty
    private ZonedDateTime endDate;
}
