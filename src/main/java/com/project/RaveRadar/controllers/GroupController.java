package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.GroupDTO;
import com.project.RaveRadar.payloads.GroupRegistration;
import com.project.RaveRadar.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/groups")
    public ResponseEntity<GroupDTO> createGroup(GroupRegistration registration){
        return groupService.createGroup(registration);
    }
}
