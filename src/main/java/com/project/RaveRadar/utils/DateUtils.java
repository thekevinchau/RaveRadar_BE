package com.project.RaveRadar.utils;

import com.project.RaveRadar.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class DateUtils {
    public static void validateStartBeforeEndDate(String name,ZonedDateTime start, ZonedDateTime end){
        if (name == null || name.isEmpty()){
            throw new BadRequestException("There is nothing to validate.");
        }
        if (start == null || end == null){
            throw new BadRequestException("Start or end date is required.");
        }
        if (start.isAfter(end)){
            throw new BadRequestException(name + "'s start date cannot be after the end date!");
        }
        if (end.isBefore(start)){
            throw new BadRequestException(name + "'s end date cannot be before the start date!");
        }
    }
}
