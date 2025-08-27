package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentReplyPayload {
    @NotBlank
    @NotNull
    private String content;
}
