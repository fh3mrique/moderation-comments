package com.projeto.comment_service.service;

import com.projeto.comment_service.api.model.CommentInput;
import com.projeto.comment_service.api.model.CommentOutput;
import com.projeto.comment_service.domain.exception.CommentNotFoundException;
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

    @Transactional()
    public CommentOutput createComment(CommentInput input) {
        Comment comment = new Comment(input);

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
