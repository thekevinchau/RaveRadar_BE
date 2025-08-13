package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnnouncementEdit {

    @Size(max = 255, message = "Header cannot exceed 255 characters!")
    private String header;
    @Size(max = 1000, message = "Content cannot exceed 1000 characters.")
    private String content;
}
