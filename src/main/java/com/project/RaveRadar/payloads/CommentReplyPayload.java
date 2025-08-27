package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentReplyPayload {
    @NotBlank
    private String comment;
}
