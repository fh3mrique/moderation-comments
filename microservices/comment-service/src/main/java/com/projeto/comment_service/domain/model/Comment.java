package com.projeto.comment_service.domain.model;

import com.projeto.comment_service.api.model.CommentInput;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Builder
public class Comment {
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String author;
    private String text;
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();


    public Comment(UUID id, String author, String text, OffsetDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
    }


    public Comment(CommentInput input) {
        this.id = UUID.randomUUID();
        this.author = input.getAuthor();
        this.text = input.getText();
        this.createdAt = OffsetDateTime.now();
    }
}
