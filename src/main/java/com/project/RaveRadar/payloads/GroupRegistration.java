package com.project.RaveRadar.payloads;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GroupRegistration {
    private String groupName;
    private String avatarUrl;
    private String description;
    private UUID eventId;
}
