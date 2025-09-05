package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private UUID id;
    private String name;
    private String avatarUrl;
    private String description;
    private UUID eventId;
    private SimpleUserProfileDTO createdBy;
    private Instant createdAt;

    public GroupDTO(Group group){
        this.name = group.getGroupName();
        this.avatarUrl = group.getGroupAvatarUrl();
        this.description = group.getDescription();
        this.eventId = group.getEvent().getId();
        this.createdBy = new SimpleUserProfileDTO(group.getCreatedBy());
        this.createdAt = group.getCreatedAt();
    }
}
