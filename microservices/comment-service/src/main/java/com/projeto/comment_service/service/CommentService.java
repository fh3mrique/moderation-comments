package com.projeto.comment_service.service;

import com.projeto.comment_service.api.client.ModerationClient;
import com.projeto.comment_service.api.model.CommentInput;
import com.projeto.comment_service.api.model.CommentOutput;
import com.projeto.comment_service.api.model.ModerationRequest;
import com.projeto.comment_service.api.model.ModerationResponse;
import com.projeto.comment_service.domain.exception.CommentNotFoundException;
import com.projeto.comment_service.domain.exception.ModerationRejectedException;
import com.projeto.comment_service.domain.model.Comment;
import com.projeto.comment_service.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModerationClient moderationClient;


    @Transactional()
    public CommentOutput createComment(CommentInput input) {
        Comment comment = Comment.builder()
                .author(input.getAuthor())
                .text(input.getText())
                .build();

       ModerationRequest moderationRequest = new ModerationRequest(comment.getId(), input.getText());

       ModerationResponse response = moderationClient.moderationComment(moderationRequest);

       if (!response.getApproved()){
           throw new ModerationRejectedException(response.getReason());
       }

        commentRepository.save(comment);

        return CommentOutput.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .author(comment.getAuthor())
                .build();
    }

    public CommentOutput getCommentById(UUID id) {

       Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);

       return  mapToOutput(comment);
    }

    public Page<CommentOutput> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(this::mapToOutput);
    }


    private CommentOutput mapToOutput(Comment comment) {
        return CommentOutput.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
