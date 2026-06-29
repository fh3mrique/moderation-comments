package com.projeto.comment_service.api.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class CommentOutput {
    private UUID id;
    private String author;
    private String text;
    private OffsetDateTime createdAt;
}
