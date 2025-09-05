package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.GroupDTO;
import com.project.RaveRadar.DTO.SimpleUserProfileDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Group;
import com.project.RaveRadar.payloads.GroupRegistration;
import com.project.RaveRadar.repositories.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final EventService eventService;
    private final UserProfileService profileService;

    public Group getGroupObj(UUID id){
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find group with ID " + id));
    }

    public ResponseEntity<GroupDTO> getGroup(UUID id){
        return ResponseEntity.ok(new GroupDTO(getGroupObj(id)));
    }

    @Transactional
    public ResponseEntity<GroupDTO> createGroup(GroupRegistration registration){
        Group newGroup = new Group();

        if (!registration.getDescription().isBlank()){
            newGroup.setDescription(registration.getDescription());
        }
        if (!registration.getAvatarUrl().isBlank()){
            newGroup.setGroupAvatarUrl(registration.getAvatarUrl());
        }
        newGroup.setGroupName(registration.getGroupName());
        newGroup.setEvent(eventService.getEventObj(registration.getEventId()));
        newGroup.setCreatedBy(profileService.getPrincipalProfile());
        Group savedGroup = groupRepository.save(newGroup);
        URI location = URI.create("/groups/" + savedGroup.getId());
        return ResponseEntity.created(location).body(new GroupDTO(savedGroup));
    }

    @Transactional
    public ResponseEntity<?> deleteGroup(UUID groupId){
        Group queriedGroup = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));
        if (queriedGroup.getCreatedBy() != profileService.getPrincipalProfile()){
            throw new ForbiddenException("You are not the owner of this group!");
        }
        groupRepository.delete(queriedGroup);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
